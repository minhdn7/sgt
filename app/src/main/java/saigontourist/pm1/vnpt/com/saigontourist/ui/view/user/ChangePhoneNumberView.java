package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangePhoneNumberView extends View {
    void onChangePhoneNumberSuccses(CommonApiResult response);
    void onChangePhoneNumberFailed(String message);
    void onChangePhoneNumberError(Throwable e);
}
