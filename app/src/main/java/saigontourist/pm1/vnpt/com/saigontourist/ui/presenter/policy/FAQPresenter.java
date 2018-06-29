package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy.FAQView;

/**
 * Created by MinhDN on 23/4/2018.
 */
public interface FAQPresenter extends Presenter<FAQView> {
    void getFAQ(String maUngDung, String token);
}
