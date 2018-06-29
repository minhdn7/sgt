package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import android.content.Context;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.GetTokenDevResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;
import timber.log.Timber;

/**
 * Created by linhl on 4/17/2018.
 */

public class GetTokenDevPresenterImpl implements GetTokenDevPresenter {

    private View view;
    @Inject
    UserInteractor userInteractor;
    @Inject
    Context context;
    private CompositeSubscription subscription;

    @Override
    public void setView(View view) {
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
    public void getTokenDev(String param1, String param2) {
        subscription.add(userInteractor.executeGetTokenDev(param1, param2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetTokenDevResult>() {
                    @Override
                    public void call(GetTokenDevResult tokenDevResult) {
                        if (tokenDevResult.getErrorCode().endsWith(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onLoadTokenDevUser(tokenDevResult.getToken());
                        } else {
                            Timber.d(tokenDevResult.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                    }
                }));
    }
}
