package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.InforEnterpriseView;

/**
 * Created by linhl on 4/24/2018.
 */

public interface InforEnterprisePresenter extends Presenter<InforEnterpriseView> {
    void getDetailInfoEnterpriseVpoint(int newId);
    void getDetailInfoEnterpriseSaigon(int newId);
}
