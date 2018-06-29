package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.SearchOnMapSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SearchOnMapVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SearchDataMapView;
import timber.log.Timber;

/**
 * Created by linhl on 4/23/2018.
 */

public class SearchDataMapPresenterImpl implements SearchDataMapPresenter {

    private CompositeSubscription subscription;
    private SearchDataMapView view;

    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(SearchDataMapView view) {
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
    public void getDataSearchVpointMap(String searchingText, int merchantId, int field, int radius, double latitude, double longitude) {
        subscription.add(specialOffersInteractor.getDataSearchVpointMap(searchingText, merchantId, field, radius, latitude, longitude)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchOnMapVpointResult>() {
                    @Override
                    public void call(SearchOnMapVpointResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetDataSearchVpointSuccses(result.getListDataMapResponses());
                        } else {
                            view.onGetDataSearchVpointFailed(result.getMessage());
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
    public void getDataSearchSaiGonMap(String token, String maUngDung, int fields,int enterprise,int chuongTrinhUuDaiId, String strSearch, int radius, double lat, double lon) {
        subscription.add(specialOffersInteractor.getListShopSaiGonOnMap( token,  maUngDung,
         fields,enterprise,chuongTrinhUuDaiId, strSearch,  radius,  lat,  lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchOnMapSaigonResult>() {
                    @Override
                    public void call(SearchOnMapSaigonResult result) {
                        if (result.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetDataSearchSaiGonSuccses(result.getData());
                        } else {
                            view.onGetDataSearchSaiGonFailed(result.getErrorDesc());
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
