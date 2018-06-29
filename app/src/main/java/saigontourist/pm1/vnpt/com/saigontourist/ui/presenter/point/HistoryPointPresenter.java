package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.HistoryPointView;

/**
 * Created by MinhDN on 23/4/2018.
 */
public interface HistoryPointPresenter extends Presenter<HistoryPointView> {
    void getHistoryPoint(String token, Integer startIndex, Integer pageSize, String fromDate, String toDate);
}
