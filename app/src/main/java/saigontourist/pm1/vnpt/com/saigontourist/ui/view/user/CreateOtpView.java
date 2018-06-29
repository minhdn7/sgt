package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 2/5/2018.
 */
public interface CreateOtpView extends View {
    void onCreateOtpSuccsess(CommonApiResult response);
    void onCreateOtpFailed(String message);
    void onCreateOtpError(Throwable e);
}

