package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialOffersView;

/**
 * Created by linhl on 4/13/2018.
 */

public interface SpecialOffersPresenter extends Presenter<SpecialOffersView> {
    void getListSpecialSaigon(String token, String maUngdung,int maTinh,
                              int thuongHieu, int linhVuc,String noiDungTimKiem, int startIndex, int pageSize);
    void getListSpecialVpoint(int category, int merchantId,
                              int locationId, String searchingTex,
                              int page, int limit, int newsTypeId);
}
