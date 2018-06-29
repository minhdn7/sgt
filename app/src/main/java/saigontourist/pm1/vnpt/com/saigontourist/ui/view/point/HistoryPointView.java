package saigontourist.pm1.vnpt.com.saigontourist.ui.view.point;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 23/4/2018.
 */
public interface HistoryPointView extends View {
    void onHistoryPointSuccses(HistoryPointResponse response);
    void onHistoryPointFailed(String message);
    void onHistoryPointError(Throwable e);
}
