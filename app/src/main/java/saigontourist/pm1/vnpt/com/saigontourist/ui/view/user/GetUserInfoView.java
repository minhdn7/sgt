package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 19/4/2018.
 */
public interface GetUserInfoView extends View {
    void onGetUserInfoSuccses(UserInfoResponse response);
    void onGetUserInfoFailed(String message);
    void onGetUserInfoError(Throwable e);
}
