package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.SubmitRegisterView;

/**
 * Created by MinhDN on 20/4/2018.
 */
public interface SubmitRegisterPresenter extends Presenter<SubmitRegisterView> {
    void submitRegister(String token, String otp);
}
