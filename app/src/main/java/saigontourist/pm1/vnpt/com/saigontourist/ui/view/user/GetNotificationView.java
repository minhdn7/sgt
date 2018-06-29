package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.NotificationResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 26/4/2018.
 */
public interface GetNotificationView extends View {
    void onNotificationSuccses(NotificationResponse response);
    void onNotificationFailed(String message);
    void onNotificationError(Throwable e);
}
