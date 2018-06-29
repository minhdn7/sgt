package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

public interface SendTokenFirebaseView extends View {
    void onSendTokenFirebaseSuccess(CommonApiResult response);
    void onSendTokenFirebaseFailed(String message);
    void onSendTokenFirebaseError(Throwable e);
}
