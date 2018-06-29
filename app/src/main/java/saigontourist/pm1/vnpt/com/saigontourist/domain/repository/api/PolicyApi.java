package saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.PolicyResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.ServiceUrl;

/**
 * Created by MinhDN on 23/4/2018.
 */
public interface PolicyApi {
    @GET(ServiceUrl.GET_FAQ)
    Observable<Response<FAQResponse>> getFAQ(
                                                @Query("MaUngDung") String maUngDung,
                                                @Query("Token") String Token);

    @POST(ServiceUrl.GET_POLICY)
    Observable<Response<PolicyResponse>> getPolicy();

}
