package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangeEmailView extends View {
    void onChangeEmailSuccses(CommonApiResult response);
    void onChangeEmailFailed(String message);
    void onChangeEmailError(Throwable e);
}
