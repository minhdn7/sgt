package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetDistrictView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy.FAQView;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface DistrictPresenter extends Presenter<GetDistrictView> {
    void getDistrict(Integer provinceId);
}
