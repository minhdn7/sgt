package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeAvatarView;
import timber.log.Timber;

public class ChangeAvatarPresenterImpl implements ChangeAvatarPresenter {

    @Inject
    UserInteractor userInteractor;
    ChangeAvatarView changeAvatarView;
    private CompositeSubscription subscription;

    @Override
    public void setView(ChangeAvatarView view) {
        changeAvatarView = view;
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
    public void changeAvatar(String tokenUser, MultipartBody.Part file) {
        subscription.add(userInteractor.changeAvatar(tokenUser, file)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonApiResult>>() {
                    @Override
                    public void call(Response<CommonApiResult> response) {
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            changeAvatarView.onChangeAvatarSuccses(response.body());
                        } else {
                            changeAvatarView.onChangeAvatarFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        changeAvatarView.onChangeAvatarError(e);
                    }
                }));
    }
}
