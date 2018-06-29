package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.policy.PolicyInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy.FAQView;
import timber.log.Timber;

/**
 * Created by MinhDN on 23/4/2018.
 */
public class FAQPresenterImpl implements FAQPresenter {

    FAQView faqView;
    private CompositeSubscription subscription;
    @Inject
    PolicyInteractor policyInteractor;

    @Override
    public void setView(FAQView view) {
        faqView = view;
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
    public void getFAQ(String maUngDung, String token) {
        subscription.add(policyInteractor.getFAQ(maUngDung, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<FAQResponse>>() {
                    @Override
                    public void call(Response<FAQResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            faqView.onGetFAQInfoSuccses(response.body());
                        } else {
                            faqView.onGetFAQInfoFailed(response.body().getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        faqView.onGetFAQInfoError(e);
                    }
                }));
    }
}