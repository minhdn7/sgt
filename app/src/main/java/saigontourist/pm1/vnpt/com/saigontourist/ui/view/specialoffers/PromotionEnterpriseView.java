package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseSpecialsSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/24/2018.
 */

public interface PromotionEnterpriseView extends View {
    void onGetPromotionEnterpriseVpointSuccses(DataCurrentOfEnterpriseVpointResult dataResult);

    void onGetPromotionEnterpriseVpointFailed(String message);

    void onGetPromotionEnterpriseSaigonSuccses(EnterpriseSpecialsSaigonResult dataResult);

    void onGetPromotionEnterpriseSaigonFailed(String message);

    void onError(Throwable e);
}
