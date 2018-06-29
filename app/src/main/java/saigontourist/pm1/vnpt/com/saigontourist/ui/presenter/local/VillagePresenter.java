package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetVillageView;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface VillagePresenter extends Presenter<GetVillageView> {
    void getVillage(Integer districtId);
}

