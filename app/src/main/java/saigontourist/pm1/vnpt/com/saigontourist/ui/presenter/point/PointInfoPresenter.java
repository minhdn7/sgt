package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.PointInfoView;

/**
 * Created by MinhDN on 20/4/2018.
 */
public interface PointInfoPresenter extends Presenter<PointInfoView> {
    void getPointInfo(String token);
}