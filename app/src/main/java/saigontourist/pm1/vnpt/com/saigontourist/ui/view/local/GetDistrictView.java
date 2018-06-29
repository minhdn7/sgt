package saigontourist.pm1.vnpt.com.saigontourist.ui.view.local;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.local.LocalResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface GetDistrictView extends View {
    void onGetDistrictSuccses(LocalResponse response);
    void onGetDistrictFailed(String message);
    void onGetDistrictError(Throwable e);
}
