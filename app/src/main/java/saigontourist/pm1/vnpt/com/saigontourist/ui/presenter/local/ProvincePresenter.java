package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetDistrictView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetProvinceView;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface ProvincePresenter extends Presenter<GetProvinceView> {
    void getProvince(String token);
}
