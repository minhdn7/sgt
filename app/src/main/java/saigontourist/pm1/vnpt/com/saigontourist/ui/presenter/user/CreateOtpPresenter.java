package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.CreateOtpView;

/**
 * Created by MinhDN on 2/5/2018.
 */
public interface CreateOtpPresenter extends Presenter<CreateOtpView> {
    void createOtp(String token, String phone);
}
