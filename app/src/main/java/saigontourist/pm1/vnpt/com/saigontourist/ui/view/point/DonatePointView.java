package saigontourist.pm1.vnpt.com.saigontourist.ui.view.point;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 27/4/2018.
 */
public interface DonatePointView extends View {
    void onDonatePointSuccess(CommonApiResult response);
    void onSendOTPSuccess(CommonApiResult response);
    void onDonatePointFailed(String message);
    void onDonatePointError(Throwable e);
}