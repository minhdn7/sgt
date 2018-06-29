package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.SearchOnMapSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SearchOnMapVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/24/2018.
 */

public interface SearchDataMapView extends View {
    void onGetDataSearchVpointSuccses(List<SearchOnMapVpointResult.ItemSearchOnMap> listData);

    void onGetDataSearchVpointFailed(String message);

    void onGetDataSearchSaiGonSuccses(List<SearchOnMapSaigonResult.Data> listData);

    void onGetDataSearchSaiGonFailed(String message);

    void onError(Throwable e);
}
