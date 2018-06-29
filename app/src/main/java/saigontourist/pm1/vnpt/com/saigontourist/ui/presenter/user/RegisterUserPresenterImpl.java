package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.RegisterUserView;
import timber.log.Timber;

/**
 * Created by linhl on 4/13/2018.
 */

public class RegisterUserPresenterImpl implements RegisterUserPresenter {

    RegisterUserView registerUserView;
    private CompositeSubscription subscription;
    @Inject
    UserInteractor userInteractor;

    @Override
    public void setView(RegisterUserView view) {
        registerUserView = view;
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
    public void registerUser(RegisterUserRequest body) {
        subscription.add(userInteractor.executeRegisterUser(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<RegisterUserResponse>>() {
                    @Override
                    public void call(Response<RegisterUserResponse> response) {
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            registerUserView.onRegisterUserSuccses(response.body());
                        } else {
                            registerUserView.onRegisterUserFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        registerUserView.onRegisterUserError(e);
                    }
                }));
    }
}
