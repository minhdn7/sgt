package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 18/4/2018.
 */
public interface LoginUserView extends View {
    void onLoginUserSuccses(LoginResponse response);

    void onLoginUserFailed(String message);
    void onLoginUserError(Throwable e);
}

