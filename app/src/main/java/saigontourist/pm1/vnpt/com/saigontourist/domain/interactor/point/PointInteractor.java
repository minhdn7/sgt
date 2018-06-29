package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point;

import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.CheckPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.PointInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.DonatePointRequest;

/**
 * Created by MinhDN on 20/4/2018.
 */
public interface PointInteractor {
    Observable<Response<PointInfoResponse>> getPointInfo(String token);
    Observable<Response<HistoryPointResponse>> getHistoryPoint(String token, Integer startIndex, Integer pageSize, String fromDate, String toDate);
    Observable<Response<CommonApiResult>> checkChucNangTangDiem(String token);
    Observable<Response<CheckPointResponse>> checkDieuKienTangDiem(String token);
    Observable<Response<CommonApiResult>> sendPoint(DonatePointRequest donatePointRequest);
}
