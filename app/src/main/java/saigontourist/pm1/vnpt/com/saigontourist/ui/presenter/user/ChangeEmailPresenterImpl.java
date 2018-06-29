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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeEmailRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeEmailView;

import timber.log.Timber;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangeEmailPresenterImpl implements ChangeEmailPresenter {

    @Inject
    UserInteractor userInteractor;

    ChangeEmailView changeEmailView;
    private CompositeSubscription subscription;


    @Override
    public void setView(ChangeEmailView view) {
        changeEmailView = view;
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
    public void changeEmail(ChangeEmailRequest request) {
        subscription.add(userInteractor.changeEmail(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonApiResult>>() {
                    @Override
                    public void call(Response<CommonApiResult> response) {
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            changeEmailView.onChangeEmailSuccses(response.body());
                        } else {
                            changeEmailView.onChangeEmailFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        changeEmailView.onChangeEmailError(e);
                    }
                }));
    }
}
