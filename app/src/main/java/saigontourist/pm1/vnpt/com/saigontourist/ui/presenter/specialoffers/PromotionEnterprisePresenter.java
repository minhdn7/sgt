package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.PromotionEnterpriseView;

/**
 * Created by linhl on 4/24/2018.
 */

public interface PromotionEnterprisePresenter extends Presenter<PromotionEnterpriseView>{
    void getListPromotionOfEnterpriseVpoint(int newId);
    void getListPromotionOfEnterpriseSaigon(int newId,int startIndex,int pageSize);
}
