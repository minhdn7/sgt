package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 20/4/2018.
 */
public interface SubmitRegisterView extends View {
    void onSubmitRegisterSuccses(CommonApiResult response);
    void onSubmitRegisterFailed(String message);
    void onSubmitRegisterError(Throwable e);
}