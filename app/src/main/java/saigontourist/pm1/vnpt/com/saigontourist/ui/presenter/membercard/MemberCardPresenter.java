package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.membercard;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.membercard.MemberCardView;

/**
 * Created by linhl on 5/7/2018.
 */

public interface MemberCardPresenter extends Presenter<MemberCardView> {
    void getMemberCardCaptcha();

    void getMemberCardBarQrCode(String token, int pinCode);

    void getMemberCardPassCode(String token, int pinCode);

    void getMemberCardAuthentication(String token, String matKhau, String captcha);

}
