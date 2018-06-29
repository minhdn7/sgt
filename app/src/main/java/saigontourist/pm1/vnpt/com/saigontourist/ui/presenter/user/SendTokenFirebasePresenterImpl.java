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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.RegisterUserView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.SendTokenFirebaseView;
import timber.log.Timber;

public class SendTokenFirebasePresenterImpl implements SendTokenFirebasePresenter{

    SendTokenFirebaseView sendTokenFirebaseView;
    private CompositeSubscription subscription;
    @Inject
    UserInteractor userInteractor;



    @Override
    public void setView(SendTokenFirebaseView view) {
        sendTokenFirebaseView = view;
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
    public void sendTokenFirebase(String phone, String os, String tokenFirebase, String deviceid, String tokenUser) {
        subscription.add(userInteractor.sendTokenFirebase(phone, os, tokenFirebase, deviceid, tokenUser)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonApiResult>>() {
                    @Override
                    public void call(Response<CommonApiResult> response) {
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            sendTokenFirebaseView.onSendTokenFirebaseSuccess(response.body());
                        } else {
                            sendTokenFirebaseView.onSendTokenFirebaseFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        sendTokenFirebaseView.onSendTokenFirebaseError(e);
                    }
                }));
    }
}
