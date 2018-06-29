package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.DetailSpecialOffersView;

/**
 * Created by linhl on 4/23/2018.
 */

public interface DetailSpecialOffersPresenter extends Presenter<DetailSpecialOffersView> {
    void getDetailSpecialTouris(String token, String maUngDung, int chuongTrinhUuDai);

    void getDetailSpecialVpoint(int newId);

    void getListShopDetailSpecialVpoint(int newId);
}
