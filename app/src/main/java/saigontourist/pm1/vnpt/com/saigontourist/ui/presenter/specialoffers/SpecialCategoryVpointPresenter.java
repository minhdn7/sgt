package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategoryVpointView;

/**
 * Created by linhl on 4/20/2018.
 */

public interface SpecialCategoryVpointPresenter extends Presenter<SpecialCategoryVpointView> {
    void getListFields();

    void getListEnterprise();

    void getListCities();
}
