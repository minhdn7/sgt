package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseShopSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseSpecialsSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.PromotionEnterpriseView;
import timber.log.Timber;

/**
 * Created by linhl on 4/24/2018.
 */

public class PromotionEnterprisePresenterImpl implements PromotionEnterprisePresenter {
    private CompositeSubscription subscription;
    PromotionEnterpriseView view;
    @Inject
    SpecialOffersInteractor specialOffersInteractor;
    @Override
    public void setView(PromotionEnterpriseView view) {
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
    public void getListPromotionOfEnterpriseVpoint(int newId) {
        subscription.add(specialOffersInteractor.getListPromotionOfEnterpriseVpoint(newId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataCurrentOfEnterpriseVpointResult>() {
                    @Override
                    public void call(DataCurrentOfEnterpriseVpointResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetPromotionEnterpriseVpointSuccses(result);
                        } else {
                            view.onGetPromotionEnterpriseVpointFailed(result.getMessage());
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
    public void getListPromotionOfEnterpriseSaigon(int newId, int startIndex, int pageSize) {
        subscription.add(specialOffersInteractor.getEnterpriseSpecialsTouris(newId,startIndex,
                pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<EnterpriseSpecialsSaigonResult>() {
                    @Override
                    public void call(EnterpriseSpecialsSaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetPromotionEnterpriseSaigonSuccses(result);
                        } else {
                            view.onGetPromotionEnterpriseVpointFailed(result.getErrorDesc());
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
