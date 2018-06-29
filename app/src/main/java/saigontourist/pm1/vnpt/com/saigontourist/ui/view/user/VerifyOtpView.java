package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 2/5/2018.
 */
public interface VerifyOtpView extends View {
    void onVerifyOtpSuccess(CommonApiResult response);
    void onVerifyOtpFailed(String message);
    void onVerifyOtpError(Throwable e);
}
