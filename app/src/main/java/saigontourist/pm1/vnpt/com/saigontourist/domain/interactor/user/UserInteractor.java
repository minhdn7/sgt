package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user;

import android.graphics.Bitmap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.AccountInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeEmailRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePassRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePhoneNumberRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeUserInfoRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ForgotPasswordRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.GetTokenDevResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.HistoryRankResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.NotificationResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;

/**
 * Created by linhl on 4/13/2018.
 */

public interface UserInteractor {
    Observable<Response<RegisterUserResponse>> executeRegisterUser(RegisterUserRequest body);
    Observable<Response<CommonApiResult>> submitRegisterUser(String token, String otp);
    Observable<GetTokenDevResult> executeGetTokenDev(String param1, String param2);
    Observable<Response<LoginResponse>> loginUser(LoginRequest request);
    Observable<Response<UserInfoResponse>> getUserInfo(String token, String deviceid);

    Observable<Response<CommonApiResult>> changeUserInfo(ChangeUserInfoRequest request);
    Observable<Response<CommonApiResult>> changePassword(ChangePassRequest request);
    Observable<Response<CommonApiResult>> changeEmail(ChangeEmailRequest request);
    Observable<Response<CommonApiResult>> changePhoneNumBer(ChangePhoneNumberRequest request);

    Observable<Response<NotificationResponse>> getNotification(String token);

    Observable<Response<CommonApiResult>> createOtp(String token, String phone);
    Observable<Response<CommonApiResult>> verifyOtp(String token, String Otp, String phone);
    Observable<Response<ResponseBody>> getCapCha();
    Observable<Response<CommonApiResult>> forgotPassword(ForgotPasswordRequest request);

    // send token firebase
    Observable<Response<CommonApiResult>> sendTokenFirebase(String phone, String os, String tokenFirebase, String deviceid, String tokenUser);

    // send avatar
    Observable<Response<CommonApiResult>> changeAvatar(String tokenUser, MultipartBody.Part file);

    // get history rank
    Observable<Response<HistoryRankResponse>> getHistoryRank(String tokenUser);

    // get thông tin tài khoản
    Observable<Response<AccountInfoResponse>> getAccountInfo(String tokenUser);
}
