package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategorySaigonView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategoryVpointView;

/**
 * Created by linhl on 4/20/2018.
 */

public interface SpecialCategorySaigonPresenter extends Presenter<SpecialCategorySaigonView> {
    void getListProvinceCode(String token);

    void getListTradeMark(String token);

    void getListFieldSTouris(String token);
}
