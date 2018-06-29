package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import javax.inject.Inject;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.AccountInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.AccountInfoView;

import timber.log.Timber;

public class AccountInfoPresenterImpl implements AccountInfoPresenter {

    @Inject
    UserInteractor userInteractor;
    AccountInfoView accountInfoView;
    private CompositeSubscription subscription;

    @Override
    public void setView(AccountInfoView view) {
        accountInfoView = view;
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
    public void getAccountInfo(String token) {
        subscription.add(userInteractor.getAccountInfo(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<AccountInfoResponse>>() {
                    @Override
                    public void call(Response<AccountInfoResponse> response) {
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            accountInfoView.onAccountInforSuccess(response.body());
                        } else {
                            accountInfoView.onAccountInfoFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        accountInfoView.onAccountInfoError(e);
                    }
                }));
    }
}