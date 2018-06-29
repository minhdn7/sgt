package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.searchmap;

import java.util.List;


public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
