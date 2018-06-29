package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeEmailRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeEmailView;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangeEmailPresenter extends Presenter<ChangeEmailView> {
    void changeEmail(ChangeEmailRequest request);
}
