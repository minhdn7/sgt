package saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.PolicyResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

public interface PolicyView extends View {
    void onPolicySuccess(PolicyResponse response);
    void onPolicyFailed(String message);
    void onPolicyError(Throwable e);
}
