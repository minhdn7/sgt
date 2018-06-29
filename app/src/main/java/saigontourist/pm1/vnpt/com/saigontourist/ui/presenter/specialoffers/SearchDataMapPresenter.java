package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.DetailSpecialOffersView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SearchDataMapView;

/**
 * Created by linhl on 4/23/2018.
 */

public interface SearchDataMapPresenter extends Presenter<SearchDataMapView> {
    void getDataSearchVpointMap(String searchingText, int merchantId, int field, int radius, double latitude, double longitude);
    void getDataSearchSaiGonMap(String token, String maUngDung,
                                int fields, int enterprise,int chuongTrinhUuDaiId,String strSearch, int radius, double lat, double lon);
}
