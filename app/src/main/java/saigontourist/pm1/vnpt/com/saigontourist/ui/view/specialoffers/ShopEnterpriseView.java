package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataStoreCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseShopSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/24/2018.
 */

public interface ShopEnterpriseView extends View {
    void onGetShopEnterpriseVpointSuccses(DataStoreCurrentOfEnterpriseVpointResult dataResult);

    void onGetShopEnterpriseVpointFailed(String message);

    void onGetShopEnterpriseSaigonSuccses(EnterpriseShopSaigonResult dataResult);

    void onGetShopEnterpriseSaigonFailed(String message);

    void onError(Throwable e);
}
