package saigontourist.pm1.vnpt.com.saigontourist.ui.view.point;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.PointInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;


/**
 * Created by MinhDN on 20/4/2018.
 */
public interface PointInfoView extends View {
    void onGetPointInfoSuccses(PointInfoResponse response);
    void onGetPointInfoFailed(String message);
    void onGetPointInfoError(Throwable e);
}
