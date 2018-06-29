package saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.local.LocalResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.ServiceUrl;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface LocalApi {
    @GET(ServiceUrl.GET_PROVINCE)
    Observable<Response<LocalResponse>> getProvince(@Query("Token") String Token);

    @GET(ServiceUrl.GET_DISTRICT)
    Observable<Response<LocalResponse>> getDistrict(@Query("TinhId") Integer TinhId);

    @GET(ServiceUrl.GET_VILLAGE)
    Observable<Response<LocalResponse>> getVillage(@Query("PhuongId") Integer PhuongId);
}
