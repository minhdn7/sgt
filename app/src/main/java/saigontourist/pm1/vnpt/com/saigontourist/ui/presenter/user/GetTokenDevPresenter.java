package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/17/2018.
 */

public interface GetTokenDevPresenter extends Presenter<View> {
    void getTokenDev(String param1,String param2);
}
