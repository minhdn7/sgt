package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 24/4/2018.
 */
public interface ChangeUserInfoView extends View {
    void onChangeUserDetailSuccses(CommonApiResult response);
    void onChangeUserDetailFailed(String message);
    void onChangeUserDetailError(Throwable e);
}
