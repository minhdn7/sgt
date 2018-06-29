package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy.PolicyView;

public interface PolicyPresenter extends Presenter<PolicyView> {
    void getPolicy();
}
