package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.DonatePointRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.DonatePointView;

/**
 * Created by MinhDN on 27/4/2018.
 */
public interface DonatePointPresenter extends Presenter<DonatePointView> {
    void sendPoint(DonatePointRequest request);
}