package saigontourist.pm1.vnpt.com.saigontourist.ui.view.point;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.CheckPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 27/4/2018.
 */
public interface DieuKienTangDiemView extends View {
    void onDieuKienTangDiemSuccses(CheckPointResponse response);
    void onDieuKienTangDiemFailed(String message);
    void onDieuKienTangDiemError(Throwable e);
}
