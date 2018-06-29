package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.ChucNangTangDiemView;

/**
 * Created by MinhDN on 27/4/2018.
 */
public interface ChucNangTangDiemPresenter extends Presenter<ChucNangTangDiemView> {
    void checkTangDiem(String token);
}
