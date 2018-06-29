package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.membercard;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StatusCode;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point.PointInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataMemberCardResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.ChucNangTangDiemPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.membercard.MemberCardView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.ChucNangTangDiemView;
import timber.log.Timber;

public class MemberCardPresenterImpl implements MemberCardPresenter {

    MemberCardView view;
    private CompositeSubscription subscription;
    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(MemberCardView view) {
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
    public void getMemberCardCaptcha() {
        subscription.add(specialOffersInteractor.getMemberCardCaptcha()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody response) {
                        if (response != null) {
                            view.onGetMemberCardCaptchaSuccses(response);
                        } else {
                            view.onGetMemberCardFailed("Không có kết nối từ server");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onError(e);
                    }
                }));
    }

    @Override
    public void getMemberCardBarQrCode(String token, int pinCode) {
        subscription.add(specialOffersInteractor.getMemberCardBarQrCode(token, pinCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataMemberCardResult>() {
                    @Override
                    public void call(DataMemberCardResult response) {
                        if (response.getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetMemberCardBarQrCodeSuccses(response);
                        } else {
                            view.onGetMemberCardFailed(response.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onError(e);
                    }
                }));
    }

    @Override
    public void getMemberCardPassCode(String token, int pinCode) {
        subscription.add(specialOffersInteractor.getMemberCardPassCode(token, pinCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonApiResult>() {
                    @Override
                    public void call(CommonApiResult response) {
                        if (response.getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetMemberCardPassCodeSuccses(response);
                        } else {
                            view.onGetMemberCardFailed(response.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onError(e);
                    }
                }));

    }

    @Override
    public void getMemberCardAuthentication(String token, String matKhau, String captcha) {
        subscription.add(specialOffersInteractor.getMemberCardAuthentication(token, matKhau, captcha)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CommonApiResult>() {
                    @Override
                    public void call(CommonApiResult response) {
                        if (response.getErrorCode().equals(StatusCode.RESPONSE_ERROR_CODE_00)) {
                            view.onGetMemberCardAuthenticationSuccses(response);
                        } else {
                            view.onGetMemberCardFailed(response.getErrorDesc());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onError(e);
                    }
                }));
    }
}

