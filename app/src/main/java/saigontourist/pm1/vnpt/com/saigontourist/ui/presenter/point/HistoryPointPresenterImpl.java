package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point.PointInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.HistoryPointView;
import timber.log.Timber;

/**
 * Created by MinhDN on 23/4/2018.
 */
public class HistoryPointPresenterImpl implements HistoryPointPresenter {

    HistoryPointView historyPointView;
    private CompositeSubscription subscription;
    @Inject
    PointInteractor pointInteractor;

    @Override
    public void setView(HistoryPointView view) {
        historyPointView = view;
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
    public void getHistoryPoint(String token, Integer startIndex, Integer pageSize, String fromDate, String toDate) {
        subscription.add(pointInteractor.getHistoryPoint(token, startIndex, pageSize, fromDate, toDate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<HistoryPointResponse>>() {
                    @Override
                    public void call(Response<HistoryPointResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            historyPointView.onHistoryPointSuccses(response.body());
                        } else {
                            historyPointView.onHistoryPointFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        historyPointView.onHistoryPointError(e);
                    }
                }));
    }
}
