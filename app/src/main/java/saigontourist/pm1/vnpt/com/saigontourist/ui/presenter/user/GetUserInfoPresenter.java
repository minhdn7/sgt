package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetUserInfoView;

/**
 * Created by MinhDN on 19/4/2018.
 */
public interface GetUserInfoPresenter extends Presenter<GetUserInfoView> {
    void getUserInfo(String token, String deviceId);
}