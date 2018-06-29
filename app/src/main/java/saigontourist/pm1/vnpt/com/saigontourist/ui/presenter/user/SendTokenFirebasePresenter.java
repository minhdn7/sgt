package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.SendTokenFirebaseView;

public interface SendTokenFirebasePresenter extends Presenter<SendTokenFirebaseView> {
    void sendTokenFirebase(String phone, String os, String tokenFirebase, String deviceid, String tokenUser);
}
