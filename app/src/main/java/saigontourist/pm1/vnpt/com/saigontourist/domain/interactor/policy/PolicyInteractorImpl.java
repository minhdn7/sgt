package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.policy;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.PolicyResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.PolicyApi;

/**
 * Created by MinhDN on 23/4/2018.
 */
public class PolicyInteractorImpl implements PolicyInteractor{
    @Inject
    PolicyApi policyApi;

    @Override
    public Observable<Response<FAQResponse>> getFAQ(String maUngDung, String token) {
        return policyApi.getFAQ(maUngDung, token);
    }

    @Override
    public Observable<Response<PolicyResponse>> getPolicy() {
        return policyApi.getPolicy();
    }
}
