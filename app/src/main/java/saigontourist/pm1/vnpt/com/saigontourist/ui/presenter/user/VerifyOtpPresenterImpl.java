package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.VerifyOtpView;
import timber.log.Timber;

/**
 * Created by MinhDN on 2/5/2018.
 */
public class VerifyOtpPresenterImpl implements VerifyOtpPresenter{
    VerifyOtpView verifyOtpView;
    private CompositeSubscription subscription;
    @Inject
    UserInteractor userInteractor;

    @Override
    public void setView(VerifyOtpView view) {
        verifyOtpView = view;
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
    public void verifyOtp(String token, String Otp, String phone) {
        subscription.add(userInteractor.verifyOtp(token, Otp, phone)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonApiResult>>() {
                    @Override
                    public void call(Response<CommonApiResult> response) {
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            verifyOtpView.onVerifyOtpSuccess(response.body());
                        } else {
                            verifyOtpView.onVerifyOtpFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        verifyOtpView.onVerifyOtpError(e);
                    }
                }));
    }
}
