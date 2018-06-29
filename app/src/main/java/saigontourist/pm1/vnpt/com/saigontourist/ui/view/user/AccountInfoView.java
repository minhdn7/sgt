package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.AccountInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

public interface AccountInfoView extends View{
    void onAccountInforSuccess(AccountInfoResponse response);
    void onAccountInfoFailed(String message);
    void onAccountInfoError(Throwable e);
}
