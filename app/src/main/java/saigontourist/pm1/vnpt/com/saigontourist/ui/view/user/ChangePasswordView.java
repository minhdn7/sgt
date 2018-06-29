package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangePasswordView extends View {
    void onChangePasswordSuccses(CommonApiResult response);
    void onChangePasswordFailed(String message);
    void onChangePasswordError(Throwable e);
}
