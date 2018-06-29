package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point.PointInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.CheckPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.ChucNangTangDiemView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.DieuKienTangDiemView;
import timber.log.Timber;

/**
 * Created by MinhDN on 27/4/2018.
 */
public class DieuKienTangDiemPresenterImpl implements DieuKienTangDiemPresenter{

    DieuKienTangDiemView view;
    private CompositeSubscription subscription;
    @Inject
    PointInteractor pointInteractor;

    @Override
    public void setView(DieuKienTangDiemView view) {
        this.view = view;
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
    public void checkTangDiem(String token) {
        subscription.add(pointInteractor.checkDieuKienTangDiem(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CheckPointResponse>>() {
                    @Override
                    public void call(Response<CheckPointResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onDieuKienTangDiemSuccses(response.body());
                        } else {
                            view.onDieuKienTangDiemFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onDieuKienTangDiemError(e);
                    }
                }));
    }
}
