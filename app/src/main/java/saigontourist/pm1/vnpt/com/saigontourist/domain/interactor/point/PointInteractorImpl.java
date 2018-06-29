package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.CheckPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.PointInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.DonatePointRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.UserApi;

/**
 * Created by MinhDN on 20/4/2018.
 */
public class PointInteractorImpl implements PointInteractor{
    @Inject
    UserApi userApi;

    @Override
    public Observable<Response<PointInfoResponse>> getPointInfo(String token) {
        return userApi.getPointInfo(token);
    }

    @Override
    public Observable<Response<HistoryPointResponse>> getHistoryPoint(String token, Integer startIndex, Integer pageSize, String fromDate, String toDate) {
        return userApi.getHistoryPoint(token, startIndex, pageSize, fromDate, toDate);
    }

    @Override
    public Observable<Response<CommonApiResult>> checkChucNangTangDiem(String token) {
        return userApi.checkChucNangTangDiem(token);
    }

    @Override
    public Observable<Response<CheckPointResponse>> checkDieuKienTangDiem(String token) {
        return userApi.checkDieuKienTangDiem(token);
    }

    @Override
    public Observable<Response<CommonApiResult>> sendPoint(DonatePointRequest donatePointRequest) {
        return userApi.sendPoint(donatePointRequest);
    }
}
