package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/24/2018.
 */

public interface InforEnterpriseView extends View {
    void onGetInfoEnterpriseVpointSuccses(InfoEnterpriseResult dataResult);

    void onGetInfoEnterpriseVpointFailed(String message);

    void onGetInfoEnterpriseSaigonSuccses(InfoEnterpriseSaigonResult dataResult);

    void onGetInfoEnterpriseSaigonFailed(String message);

    void onError(Throwable e);
}
