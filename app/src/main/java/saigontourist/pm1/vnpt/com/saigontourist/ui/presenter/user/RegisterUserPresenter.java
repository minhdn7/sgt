package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.RegisterUserView;

/**
 * Created by linhl on 4/13/2018.
 */

public interface RegisterUserPresenter extends Presenter<RegisterUserView> {
    void registerUser(RegisterUserRequest body);
}
