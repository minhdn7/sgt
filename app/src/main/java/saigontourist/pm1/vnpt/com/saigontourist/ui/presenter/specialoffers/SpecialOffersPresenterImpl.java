package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialOffersView;
import timber.log.Timber;

/**
 * Created by linhl on 4/13/2018.
 */

public class SpecialOffersPresenterImpl implements SpecialOffersPresenter {

    SpecialOffersView view;
    private CompositeSubscription subscription;

    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(SpecialOffersView view) {
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
    public void getListSpecialSaigon(String token, String maUngdung, int maTinh,
                                     int thuongHieu, int linhVuc, String noiDungTimKiem, int startIndex, int pageSize) {
        subscription.add(specialOffersInteractor.executeSaigonSpecialOffres(token, maUngdung
                , maTinh, thuongHieu, linhVuc, noiDungTimKiem, startIndex, pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SpecialOffersSaigonResult>() {
                    @Override
                    public void call(SpecialOffersSaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetListSaigonSuccses(result.getDataOffers().getListSpecial());
                        } else {
                            view.onGetListSaigonFailed(result.getErrorDesc());
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
    public void getListSpecialVpoint(int category, int merchantId, int locationId, String searchingTex,
                                     int page, int limit, int newsTypeId) {
        subscription.add(specialOffersInteractor.executeVpointSpecialOffres(category, merchantId, locationId
                , searchingTex, page, limit, newsTypeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SpecialOffersVpointResult>() {
                    @Override
                    public void call(SpecialOffersVpointResult result) {
                        if (result.getErrorCode() == 0) {
                            view.onGetListVpointSuccses(result.getDataDanhSachTinTucResponeList());
                        } else {
                            view.onGetListVpointFailed(result.getMessage());
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
