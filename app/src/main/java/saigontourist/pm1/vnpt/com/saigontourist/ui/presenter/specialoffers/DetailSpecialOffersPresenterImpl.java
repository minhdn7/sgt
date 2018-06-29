package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.DetailSpecialOffersView;
import timber.log.Timber;

/**
 * Created by linhl on 4/23/2018.
 */

public class DetailSpecialOffersPresenterImpl implements DetailSpecialOffersPresenter {

    private CompositeSubscription subscription;
    private DetailSpecialOffersView view;

    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(DetailSpecialOffersView view) {
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
    public void getDetailSpecialTouris(String token, String maUngDung, int chuongTrinhUuDai) {
        subscription.add(specialOffersInteractor.getDetailSpecialTouris(token, maUngDung, chuongTrinhUuDai)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataDetailSpecialSaigonResult>() {
                    @Override
                    public void call(DataDetailSpecialSaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetDetailSpecialTourisSuccses(result);
                        } else {
                            view.onGetDetailSpecialTourisFailed(result.getErrorDesc());
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
    public void getDetailSpecialVpoint(int newId) {
        subscription.add(specialOffersInteractor.getDetailSpecialVpoint(newId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataDetailSpecialVpointResult>() {
                    @Override
                    public void call(DataDetailSpecialVpointResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetDetailSpecialVpointSuccses(result);
                        } else {
                            view.onGetDetailSpecialVpointFailed(result.getMessage());
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
    public void getListShopDetailSpecialVpoint(int newId) {
        subscription.add(specialOffersInteractor.getListShopDetailSpecialVpoint(newId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataDetailSpecialVpointResult.DataListShopByNewResult>() {
                    @Override
                    public void call(DataDetailSpecialVpointResult.DataListShopByNewResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetListShopDetailSpecialVpointSuccses(result);
                        } else {
                            view.onGetListShopDetailSpecialVpointFailed(result.getMessage());
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
