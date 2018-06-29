package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/18/2018.
 */


public interface SpecialOffersView extends View {
    void onGetListSaigonSuccses(List<SpecialOffersSaigonResult.SpecialOffer> listSpecial);

    void onGetListVpointSuccses(List<SpecialOffersVpointResult.DataOffersVpoint> dataDanhSachTinTucResponeList);

    void onGetListVpointFailed(String message);

    void onGetListSaigonFailed(String message);

    void onGetListError(Throwable e);
}
