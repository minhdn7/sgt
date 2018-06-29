package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.local;

import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.local.LocalResponse;

/**
 * Created by MinhDN on 4/5/2018.
 */
public interface LocalInteractor {
    Observable<Response<LocalResponse>> getProvince(String token);
    Observable<Response<LocalResponse>> getDistrict(Integer provinceId);
    Observable<Response<LocalResponse>> getVillage(Integer districtId);
}
