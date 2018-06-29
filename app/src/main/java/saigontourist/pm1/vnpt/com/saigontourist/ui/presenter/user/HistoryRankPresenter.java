package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import okhttp3.MultipartBody;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.Presenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.HistoryRankView;

public interface HistoryRankPresenter extends Presenter<HistoryRankView> {
    void getHistoryRankPresenter(String tokenUser);
}
