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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePassRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePhoneNumberRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangePasswordView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangePhoneNumberView;
import timber.log.Timber;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangePhoneNumberPresenterImpl implements ChangePhoneNumberPresenter {

    @Inject
    UserInteractor userInteractor;

    ChangePhoneNumberView changePhoneNumberView;
    private CompositeSubscription subscription;


    @Override
    public void setView(ChangePhoneNumberView view) {
        changePhoneNumberView = view;
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
    public void changePhoneNumber(ChangePhoneNumberRequest request) {
        subscription.add(userInteractor.changePhoneNumBer(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonApiResult>>() {
                    @Override
                    public void call(Response<CommonApiResult> response) {
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            changePhoneNumberView.onChangePhoneNumberSuccses(response.body());
                        } else {
                            changePhoneNumberView.onChangePhoneNumberFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        changePhoneNumberView.onChangePhoneNumberError(e);
                    }
                }));
    }
}
