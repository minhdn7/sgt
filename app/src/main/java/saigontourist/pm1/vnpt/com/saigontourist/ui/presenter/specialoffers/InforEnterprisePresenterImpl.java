package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StringUtils;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.InforEnterpriseView;
import timber.log.Timber;

/**
 * Created by linhl on 4/24/2018.
 */

public class InforEnterprisePresenterImpl implements InforEnterprisePresenter {
    InforEnterpriseView view;
    private CompositeSubscription subscription;
    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(InforEnterpriseView view) {
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
    public void getDetailInfoEnterpriseVpoint(int newId) {
        subscription.add(specialOffersInteractor.getDetailInfoEnterpriseVpoint(newId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<InfoEnterpriseResult>() {
                    @Override
                    public void call(InfoEnterpriseResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetInfoEnterpriseVpointSuccses(result);
                        } else {
                            view.onGetInfoEnterpriseVpointFailed(result.getMessage());
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
    public void getDetailInfoEnterpriseSaigon(int newId) {
        subscription.add(specialOffersInteractor.getInforEnterpriseTouris(newId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<InfoEnterpriseSaigonResult>() {
                    @Override
                    public void call(InfoEnterpriseSaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00) ) {
                            view.onGetInfoEnterpriseSaigonSuccses(result);
                        } else {
                            view.onGetInfoEnterpriseSaigonFailed(result.getErrorDesc());
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
