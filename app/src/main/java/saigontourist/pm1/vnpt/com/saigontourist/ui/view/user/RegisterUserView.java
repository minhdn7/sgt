package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/13/2018.
 */

public interface RegisterUserView extends View {
    void onRegisterUserSuccses(RegisterUserResponse response);
    void onRegisterUserFailed(String message);
    void onRegisterUserError(Throwable e);
}
