package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.HistoryRankResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

public interface HistoryRankView extends View{
    void onGetHistorySuccess(HistoryRankResponse response);
    void onGetHistoryFailed(String message);
    void onGetHistoryError(Throwable e);
}
