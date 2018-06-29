package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePhoneNumberRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangePhoneNumberView;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangePhoneNumberPresenter extends Presenter<ChangePhoneNumberView> {
    void changePhoneNumber(ChangePhoneNumberRequest request);
}
