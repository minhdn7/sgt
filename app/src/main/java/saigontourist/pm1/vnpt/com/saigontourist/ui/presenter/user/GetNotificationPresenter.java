package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetNotificationView;

/**
 * Created by MinhDN on 26/4/2018.
 */
public interface GetNotificationPresenter extends Presenter<GetNotificationView> {
    void getNotification(String token);
}
