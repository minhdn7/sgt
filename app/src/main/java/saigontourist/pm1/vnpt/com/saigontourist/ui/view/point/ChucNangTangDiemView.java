package saigontourist.pm1.vnpt.com.saigontourist.ui.view.point;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 27/4/2018.
 */
public interface ChucNangTangDiemView extends View {
    void onChucNangTangDiemSuccses(CommonApiResult response);
    void onChucNangTangDiemFailed(String message);
    void onChucNangTangDiemError(Throwable e);
}
