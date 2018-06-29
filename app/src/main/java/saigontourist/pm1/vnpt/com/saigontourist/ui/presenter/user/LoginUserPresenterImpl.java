package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.LoginUserView;

import timber.log.Timber;

/**
 * Created by MinhDN on 19/4/2018.
 */
public class LoginUserPresenterImpl implements LoginUserPresenter {

    LoginUserView loginUserView;
    private CompositeSubscription subscription;
    @Inject
    UserInteractor userInteractor;

    @Override
    public void setView(LoginUserView view) {
        loginUserView = view;
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
    public void loginUser(LoginRequest request) {
        subscription.add(userInteractor.loginUser(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<LoginResponse>>() {
                    @Override
                    public void call(Response<LoginResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            loginUserView.onLoginUserSuccses(response.body());
                        } else {
                            loginUserView.onLoginUserFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        loginUserView.onLoginUserError(e);
                    }
                }));
    }
}