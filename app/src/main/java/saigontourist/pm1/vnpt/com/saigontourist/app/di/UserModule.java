package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point.PointInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.point.PointInteractorImpl;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractorImpl;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.UserApi;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.LoginActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.ChucNangTangDiemPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.ChucNangTangDiemPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.DieuKienTangDiemPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.DieuKienTangDiemPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.DonatePointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.DonatePointPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.HistoryPointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.HistoryPointPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.PointInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.PointInfoPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.AccountInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.AccountInfoPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeAvatarPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeAvatarPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeEmailPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeEmailPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangePasswordPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangePasswordPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangePhoneNumberPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangePhoneNumberPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeUserInfoPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.CreateOtpPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.CreateOtpPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ForgotPasswordPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ForgotPasswordPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetCapchaPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetCapchaPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetNotificationPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetNotificationPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.HistoryRankPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.HistoryRankPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.LoginUserPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.LoginUserPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.SendTokenFirebasePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.SendTokenFirebasePresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.SubmitRegisterPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.SubmitRegisterPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.VerifyOtpPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.VerifyOtpPresenterImpl;

/**
 * Created by MinhDN on 18/4/2018.
 */
@Module(complete = false, library = true)
public class UserModule {
    private String svSaiGonT = "server_saigon";
    private String svVpoint = "server_vpoint";
    @Provides
    UserInteractor provideUserInteractor(UserInteractorImpl userInteractor) {
        return userInteractor;
    }

    @Provides
    PointInteractor providePointInteractor(PointInteractorImpl pointInteractor) {
        return pointInteractor;
    }

    @Provides
    UserApi provideRegisterApi(@Named("server_saigon") Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(UserApi.class);
    }

    @Provides
    LoginUserPresenter loginUserPresenter(LoginUserPresenterImpl loginUserPresenter) {
        return loginUserPresenter;
    }

    @Provides
    GetUserInfoPresenter getUserInfoPresenter(GetUserInfoPresenterImpl getUserInfoPresenter) {
        return getUserInfoPresenter;
    }

    @Provides
    SubmitRegisterPresenter submitRegisterPresenter(SubmitRegisterPresenterImpl submitRegisterPresenter) {
        return submitRegisterPresenter;
    }

    @Provides
    PointInfoPresenter pointInfoPresenter(PointInfoPresenterImpl pointInfoPresenter) {
        return pointInfoPresenter;
    }

    @Provides
    HistoryPointPresenter historyPointPresenter(HistoryPointPresenterImpl historyPointPresenter) {
        return historyPointPresenter;
    }

    @Provides
    ChucNangTangDiemPresenter chucNangTangDiemPresenter(ChucNangTangDiemPresenterImpl chucNangTangDiemPresenter) {
        return chucNangTangDiemPresenter;
    }

    @Provides
    DieuKienTangDiemPresenter dieuKienTangDiemPresenter(DieuKienTangDiemPresenterImpl dieuKienTangDiemPresenter) {
        return dieuKienTangDiemPresenter;
    }

    @Provides
    DonatePointPresenter donatePointPresenter(DonatePointPresenterImpl donatePointPresenter) {
        return donatePointPresenter;
    }

    @Provides
    ChangePasswordPresenter changePasswordPresenter(ChangePasswordPresenterImpl changePasswordPresenter) {
        return changePasswordPresenter;
    }

    @Provides
    ChangeEmailPresenter changeEmailPresenter(ChangeEmailPresenterImpl changeEmailPresenter) {
        return changeEmailPresenter;
    }

    @Provides
    ChangePhoneNumberPresenter changePhoneNumberPresenter(ChangePhoneNumberPresenterImpl changePhoneNumberPresenter) {
        return changePhoneNumberPresenter;
    }

    @Provides
    ChangeUserInfoPresenter changeUserInfoPresenter(ChangeUserInfoPresenterImpl changeUserInfoPresenter) {
        return changeUserInfoPresenter;
    }

    @Provides
    GetNotificationPresenter getNotificationPresenter(GetNotificationPresenterImpl getNotificationPresenter) {
        return getNotificationPresenter;
    }

    @Provides
    CreateOtpPresenter createOtpPresenter(CreateOtpPresenterImpl createOtpPresenter) {
        return createOtpPresenter;
    }

    @Provides
    VerifyOtpPresenter verifyOtpPresenter(VerifyOtpPresenterImpl verifyOtpPresenter) {
        return verifyOtpPresenter;
    }

    @Provides
    GetCapchaPresenter getCapchaPresenter(GetCapchaPresenterImpl getCapchaPresenter) {
        return getCapchaPresenter;
    }

    @Provides
    ForgotPasswordPresenter forgotPasswordPresenter(ForgotPasswordPresenterImpl forgotPasswordPresenter) {
        return forgotPasswordPresenter;
    }

    @Provides
    SendTokenFirebasePresenter sendTokenFirebasePresenter(SendTokenFirebasePresenterImpl sendTokenFirebasePresenter) {
        return sendTokenFirebasePresenter;
    }

    @Provides
    ChangeAvatarPresenter changeAvatarPresenter(ChangeAvatarPresenterImpl changeAvatarPresenter) {
        return changeAvatarPresenter;
    }

    @Provides
    HistoryRankPresenter historyRankPresenter(HistoryRankPresenterImpl historyRankPresenter) {
        return historyRankPresenter;
    }

    @Provides
    AccountInfoPresenter accountInfoPresenter(AccountInfoPresenterImpl accountInfoPresenter) {
        return accountInfoPresenter;
    }
}
