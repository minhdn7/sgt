package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ForgotPasswordRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ForgotPasswordView;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface ForgotPasswordPresenter extends Presenter<ForgotPasswordView> {
    void forgotPassword(ForgotPasswordRequest request);
}