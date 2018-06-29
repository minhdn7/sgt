package saigontourist.pm1.vnpt.com.saigontourist.ui.event;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;

/**
 * Created by MinhDN on 19/4/2018.
 */
public class UserInfoEvent {
    @Getter @Setter
    private UserInfoResponse userInfoResponse;

    public UserInfoEvent(UserInfoResponse userInfoResponse) {
        this.userInfoResponse = userInfoResponse;
    }
}
