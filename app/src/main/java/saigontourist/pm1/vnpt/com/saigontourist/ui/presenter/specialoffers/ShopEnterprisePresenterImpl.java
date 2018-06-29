package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataStoreCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseShopSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.ShopEnterpriseView;
import timber.log.Timber;

/**
 * Created by linhl on 4/24/2018.
 */

public class ShopEnterprisePresenterImpl implements ShopEnterprisePresenter {
    private CompositeSubscription subscription;
    ShopEnterpriseView view;
    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(ShopEnterpriseView view) {
        this.view = view;
    }

    @Override
    public void onViewCreate() {
        subscription = new CompositeSubscription();
    }

    @Override
    public void onViewStart() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewStop() {

    }

    @Override
    public void onViewDestroy() {
        subscription.unsubscribe();
    }

    @Override
    public void getListShopOfEnterpriseVpoint(int newId) {
        subscription.add(specialOffersInteractor.getListShopOfEnterpriseVpoint(newId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataStoreCurrentOfEnterpriseVpointResult>() {
                    @Override
                    public void call(DataStoreCurrentOfEnterpriseVpointResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetShopEnterpriseVpointSuccses(result);
                        } else {
                            view.onGetShopEnterpriseVpointFailed(result.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onError(e);
                    }
                }));
    }

    @Override
    public void getListShopOfEnterpriseSaigon(int newId) {
        subscription.add(specialOffersInteractor.getEnterpriseShopTouris(newId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<EnterpriseShopSaigonResult>() {
                    @Override
                    public void call(EnterpriseShopSaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetShopEnterpriseSaigonSuccses(result);
                        } else {
                            view.onGetShopEnterpriseSaigonFailed(result.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onError(e);
                    }
                }));
    }
}
