package saigontourist.pm1.vnpt.com.saigontourist.ui.view.membercard;

import okhttp3.ResponseBody;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataMemberCardResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 5/7/2018.
 */

public interface MemberCardView extends View {

    void onGetMemberCardCaptchaSuccses(ResponseBody responseBody);

    void onGetMemberCardBarQrCodeSuccses(DataMemberCardResult result);

    void onGetMemberCardPassCodeSuccses(CommonApiResult dataResult);

    void onGetMemberCardAuthenticationSuccses(CommonApiResult result);

    void onGetMemberCardFailed(String message);
    void onError(Throwable e);
}
