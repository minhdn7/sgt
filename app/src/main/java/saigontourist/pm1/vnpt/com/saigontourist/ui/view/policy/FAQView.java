package saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 23/4/2018.
 */
public interface FAQView extends View {
    void onGetFAQInfoSuccses(FAQResponse response);
    void onGetFAQInfoFailed(String message);
    void onGetFAQInfoError(Throwable e);
}

