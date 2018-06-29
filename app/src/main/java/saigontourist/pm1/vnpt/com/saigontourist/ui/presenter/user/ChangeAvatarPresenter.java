package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import okhttp3.MultipartBody;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeAvatarView;

public interface ChangeAvatarPresenter extends Presenter<ChangeAvatarView> {
    void changeAvatar(String tokenUser, MultipartBody.Part file);
}
