package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import javax.inject.Inject;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;

import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetCapChaView;

import timber.log.Timber;

/**
 * Created by MinhDN on 3/5/2018.
 */
public class GetCapchaPresenterImpl implements GetCapchaPresenter{
    GetCapChaView getCapChaView;
    private CompositeSubscription subscription;
    @Inject
    UserInteractor userInteractor;

    @Override
    public void setView(GetCapChaView view) {
        getCapChaView = view;
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
    public void getCapcha() {
        subscription.add(userInteractor.getCapCha()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ResponseBody>>() {
                    @Override
                    public void call(Response<ResponseBody> response) {
                        String url = response.toString();
                        Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                        getCapChaView.onLoadCapChaSuccess(bm);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        getCapChaView.onLoadCapChaError(e);
                    }
                }));
    }
}

