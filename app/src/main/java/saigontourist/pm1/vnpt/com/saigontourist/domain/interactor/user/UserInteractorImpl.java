package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user;

import android.graphics.Bitmap;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.AccountInfoRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.AccountInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeEmailRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePassRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePhoneNumberRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeUserInfoRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.CreateOtpRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ForgotPasswordRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.GetTokenDevRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.GetTokenDevResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.HistoryRankResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.NotificationResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.UserApi;

/**
 * Created by linhl on 4/13/2018.
 */

public class UserInteractorImpl implements UserInteractor {
    @Inject
    UserApi userApi;

    @Override
    public Observable<Response<RegisterUserResponse>> executeRegisterUser(RegisterUserRequest body) {
        return userApi.registerUserApi(body.getHoTen(),
                                        body.getTenDangNhap(),
                                        body.getMatKhau(),
                                        body.getEmail(),
                                        body.getSoDienThoai(),
                                        body.getNgaySinh(),
                                        body.getDiaChi(),
                                        body.getGioiTinh());
    }

    @Override
    public Observable<Response<CommonApiResult>> submitRegisterUser(String token, String otp) {
        return userApi.submitRegisterApi(token, otp);
    }

    @Override
    public Observable<GetTokenDevResult> executeGetTokenDev(String param1, String param2) {
        return userApi.getTokenDev(new GetTokenDevRequest(param1, param2));
    }

    @Override
    public Observable<Response<LoginResponse>> loginUser(LoginRequest request) {
        return userApi.loginUser(request);
    }

    @Override
    public Observable<Response<UserInfoResponse>> getUserInfo(String token, String deviceid) {
        return userApi.getUserInfo(deviceid, token);
    }

    @Override
    public Observable<Response<CommonApiResult>> changeUserInfo(ChangeUserInfoRequest request) {
        return userApi.changeUserInfo(request);
    }

    @Override
    public Observable<Response<CommonApiResult>> changePassword(ChangePassRequest request) {
        return userApi.changePassword(request);
    }

    @Override
    public Observable<Response<CommonApiResult>> changeEmail(ChangeEmailRequest request) {
        return userApi.changeEmail(request);
    }

    @Override
    public Observable<Response<CommonApiResult>> changePhoneNumBer(ChangePhoneNumberRequest request) {
        return userApi.changePhoneNumber(request);
    }

    @Override
    public Observable<Response<NotificationResponse>> getNotification(String token) {
        return userApi.getNotification(token);
    }

    @Override
    public Observable<Response<CommonApiResult>> createOtp(String token, String phone) {
        CreateOtpRequest request = new CreateOtpRequest(phone, token);
        return userApi.createOTP(request);
    }

    @Override
    public Observable<Response<CommonApiResult>> verifyOtp(String token, String Otp, String phone) {
        return userApi.verifyOTP(token, Otp, phone);
    }

    @Override
    public Observable<Response<ResponseBody>> getCapCha() {
        return userApi.getCapCha();
    }

    @Override
    public Observable<Response<CommonApiResult>> forgotPassword(ForgotPasswordRequest request) {
        return userApi.forgotPassword(request);
    }

    @Override
    public Observable<Response<CommonApiResult>> sendTokenFirebase(String phone, String os, String tokenFirebase, String deviceid, String tokenUser) {
        return userApi.sendTokenFirebase(phone, os, tokenFirebase, deviceid, tokenUser);
    }

    @Override
    public Observable<Response<CommonApiResult>> changeAvatar(String tokenUser, MultipartBody.Part file) {
        return userApi.changeAvatar(tokenUser, file);
    }

    @Override
    public Observable<Response<HistoryRankResponse>> getHistoryRank(String tokenUser) {
        return userApi.getHistoryRank(tokenUser);
    }

    @Override
    public Observable<Response<AccountInfoResponse>> getAccountInfo(String tokenUser) {
        AccountInfoRequest request = new AccountInfoRequest(tokenUser);
        return userApi.getAccountInfo(request);
    }
}
