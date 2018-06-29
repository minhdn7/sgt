package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.local.LocalInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.local.LocalResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetDistrictView;

import timber.log.Timber;

/**
 * Created by MinhDN on 4/5/2018.
 */
public class DistrictPresenterImpl implements DistrictPresenter {

    GetDistrictView getDistrictView;

    private CompositeSubscription subscription;

    @Inject
    LocalInteractor localInteractor;

    @Override
    public void setView(GetDistrictView view) {
        getDistrictView = view;
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
    public void getDistrict(Integer provinceId) {
        subscription.add(localInteractor.getDistrict(provinceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<LocalResponse>>() {
                    @Override
                    public void call(Response<LocalResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            getDistrictView.onGetDistrictSuccses(response.body());
                        } else {
                            getDistrictView.onGetDistrictFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        getDistrictView.onGetDistrictError(e);
                    }
                }));
    }
}
