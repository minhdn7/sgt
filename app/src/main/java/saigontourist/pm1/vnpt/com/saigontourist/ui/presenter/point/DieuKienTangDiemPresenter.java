package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.DieuKienTangDiemView;

/**
 * Created by MinhDN on 27/4/2018.
 */
public interface DieuKienTangDiemPresenter extends Presenter<DieuKienTangDiemView> {
    void checkTangDiem(String token);
}
