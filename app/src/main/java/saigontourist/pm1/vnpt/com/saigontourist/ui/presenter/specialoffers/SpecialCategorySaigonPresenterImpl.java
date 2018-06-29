package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategorySaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategorySaigonView;
import timber.log.Timber;

/**
 * Created by linhl on 4/20/2018.
 */

public class SpecialCategorySaigonPresenterImpl implements SpecialCategorySaigonPresenter {
    SpecialCategorySaigonView view;
    private CompositeSubscription subscription;

    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(SpecialCategorySaigonView view) {
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
    public void getListProvinceCode(String token) {
        subscription.add(specialOffersInteractor.getListProvinceCode(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataCategorySaigonResult>() {
                    @Override
                    public void call(DataCategorySaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetListProvinceCodeSuccses(result.getListData());
                        } else {
                            view.onGetListProvinceCodeFailed(result.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onGetListError(e);
                    }
                }));
    }

    @Override
    public void getListTradeMark(String token) {
        subscription.add(specialOffersInteractor.getListTradeMark(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataCategorySaigonResult>() {
                    @Override
                    public void call(DataCategorySaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetListTradeMarkSuccses(result.getListData());
                        } else {
                            view.onGetListTradeMarkFailed(result.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onGetListError(e);
                    }
                }));
    }

    @Override
    public void getListFieldSTouris(String token) {
        subscription.add(specialOffersInteractor.getListFieldSTouris(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataCategorySaigonResult>() {
                    @Override
                    public void call(DataCategorySaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetListFieldSTourisSuccses(result.getListData());
                        } else {
                            view.onGetListFieldSTourisFailed(result.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onGetListError(e);
                    }
                }));
    }
}
