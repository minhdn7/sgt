package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePassRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;

import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangePasswordView;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangePasswordPresenter extends Presenter<ChangePasswordView> {
    void changePassword(ChangePassRequest request);
}
