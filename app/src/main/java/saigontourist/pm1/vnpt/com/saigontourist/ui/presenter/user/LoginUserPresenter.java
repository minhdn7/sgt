package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.LoginUserView;

/**
 * Created by MinhDN on 18/4/2018.
 */
public interface LoginUserPresenter extends Presenter<LoginUserView> {
    void loginUser(LoginRequest request);
}
