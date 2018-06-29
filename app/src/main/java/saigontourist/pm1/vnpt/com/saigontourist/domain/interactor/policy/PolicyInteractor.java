package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.policy;

import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.PolicyResponse;

/**
 * Created by MinhDN on 23/4/2018.
 */
public interface PolicyInteractor {
    Observable<Response<FAQResponse>> getFAQ(String maUngDung, String token);

    Observable<Response<PolicyResponse>> getPolicy();
}
