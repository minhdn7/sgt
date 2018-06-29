package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.ShopEnterpriseView;

/**
 * Created by linhl on 4/24/2018.
 */

public interface ShopEnterprisePresenter extends Presenter<ShopEnterpriseView> {
    void getListShopOfEnterpriseVpoint(int newId);
    void getListShopOfEnterpriseSaigon(int newId);
}
