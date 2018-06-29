package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeUserInfoRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeUserInfoView;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangeUserInfoPresenter extends Presenter<ChangeUserInfoView> {
    void changeUserInfo(ChangeUserInfoRequest request);
}
