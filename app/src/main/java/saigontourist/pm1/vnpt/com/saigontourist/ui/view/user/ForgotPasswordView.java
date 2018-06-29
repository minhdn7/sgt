package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface ForgotPasswordView extends View {
    void onForgotPasswordSuccses(CommonApiResult response);
    void onForgotPasswordFailed(String message);
    void onForgotPasswordError(Throwable e);
}

