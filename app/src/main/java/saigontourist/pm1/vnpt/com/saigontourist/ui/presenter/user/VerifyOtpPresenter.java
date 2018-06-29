package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.VerifyOtpView;

/**
 * Created by MinhDN on 2/5/2018.
 */
public interface VerifyOtpPresenter extends Presenter<VerifyOtpView> {
    void verifyOtp(String token, String Otp, String phone);
}
