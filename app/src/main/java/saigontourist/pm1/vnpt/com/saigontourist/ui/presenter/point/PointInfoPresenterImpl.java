package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point.PointInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.PointInfoResponse;

import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.PointInfoView;

import timber.log.Timber;

/**
 * Created by MinhDN on 20/4/2018.
 */
public class PointInfoPresenterImpl implements PointInfoPresenter {

    PointInfoView pointInfoView;
    private CompositeSubscription subscription;
    @Inject
    PointInteractor pointInteractor;

    @Override
    public void setView(PointInfoView view) {
        pointInfoView = view;
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
    public void getPointInfo(String token) {
        subscription.add(pointInteractor.getPointInfo(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<PointInfoResponse>>() {
                    @Override
                    public void call(Response<PointInfoResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            pointInfoView.onGetPointInfoSuccses(response.body());
                        } else {
                            pointInfoView.onGetPointInfoFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        pointInfoView.onGetPointInfoError(e);
                    }
                }));
    }
}
