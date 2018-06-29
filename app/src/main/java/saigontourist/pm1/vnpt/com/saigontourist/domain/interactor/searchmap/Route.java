package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.searchmap;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by THAOPX on 3/15/2017.
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
