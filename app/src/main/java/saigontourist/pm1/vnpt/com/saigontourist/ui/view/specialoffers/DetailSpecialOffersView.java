package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/23/2018.
 */

public interface DetailSpecialOffersView  extends View{
    void onGetDetailSpecialVpointSuccses(DataDetailSpecialVpointResult dataResult);
    void onGetListShopDetailSpecialVpointSuccses(DataDetailSpecialVpointResult.DataListShopByNewResult dataShop);
    void onGetDetailSpecialTourisSuccses(DataDetailSpecialSaigonResult dataResult);

    void onGetDetailSpecialVpointFailed(String message);
    void onGetListShopDetailSpecialVpointFailed(String message);
    void onGetDetailSpecialTourisFailed(String message);

    void onError(Throwable e);


}
