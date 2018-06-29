package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.local;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.local.LocalResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.LocalApi;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.UserApi;

/**
 * Created by MinhDN on 4/5/2018.
 */
public class LocalInteractorImpl implements LocalInteractor {
    @Inject
    LocalApi localApi;

    @Override
    public Observable<Response<LocalResponse>> getProvince(String token) {
        return localApi.getProvince(token);
    }

    @Override
    public Observable<Response<LocalResponse>> getDistrict(Integer provinceId) {
        return localApi.getDistrict(provinceId);
    }

    @Override
    public Observable<Response<LocalResponse>> getVillage(Integer districtId) {
        return localApi.getVillage(districtId);
    }
}
