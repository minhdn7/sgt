package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.HistoryRankResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.HistoryRankView;
import timber.log.Timber;

public class HistoryRankPresenterImpl implements HistoryRankPresenter {

    HistoryRankView historyRankView;
    private CompositeSubscription subscription;
    @Inject
    UserInteractor userInteractor;

    @Override
    public void setView(HistoryRankView view) {
        historyRankView = view;
    }

    @Override
    public void onViewCreate() {
        subscription = new CompositeSubscription();
    }

    @Override
    public void onViewStart() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewStop() {

    }

    @Override
    public void onViewDestroy() {
        subscription.unsubscribe();
    }

    @Override
    public void getHistoryRankPresenter(String tokenUser) {
        subscription.add(userInteractor.getHistoryRank(tokenUser)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<HistoryRankResponse>>() {
                    @Override
                    public void call(Response<HistoryRankResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            historyRankView.onGetHistorySuccess(response.body());
                        } else {
                            historyRankView.onGetHistoryFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        historyRankView.onGetHistoryError(e);
                    }
                }));
    }

}
