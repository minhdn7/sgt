package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

public interface ChangeAvatarView extends View {
    void onChangeAvatarSuccses(CommonApiResult response);
    void onChangeAvatarFailed(String message);
    void onChangeAvatarError(Throwable e);
}
