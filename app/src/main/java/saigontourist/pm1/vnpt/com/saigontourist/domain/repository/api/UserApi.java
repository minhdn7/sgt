package saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api;

import android.graphics.Bitmap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.CheckPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.PointInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.DonatePointRequest;
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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.ServiceUrl;

/**
 * Created by linhl on 4/13/2018.
 */

public interface UserApi {
    /*@FormUrlEncoded
    @POST("/apiLogin/GetTokenNhaPhatTrien")
    Observable<GetTokenDevResult> getTokenDev(@Field("Para1") String param1, @Field("Para2") String param2);*/
    @POST("/apiLogin/GetTokenNhaPhatTrien")
    Observable<GetTokenDevResult> getTokenDev(@Body GetTokenDevRequest request);

    // dang ky
    @FormUrlEncoded
    @POST(ServiceUrl.POST_REGISTER_USER)
    Observable<Response<RegisterUserResponse>> registerUserApi(@Field("HoTen") String HoTen,
                                                               @Field("TenDangNhap") String TenDangNhap,
                                                               @Field("MatKhau") String MatKhau,
                                                               @Field("Email") String Email,
                                                               @Field("SoDienThoai") String SoDienThoai,
                                                               @Field("NgaySinh") String NgaySinh,
                                                               @Field("DiaChi") String DiaChi,
                                                               @Field("GioiTinh") Integer GioiTinh
                                                               );

    @FormUrlEncoded
    @POST(ServiceUrl.POST_SUBMIT_REGISTER)
    Observable<Response<CommonApiResult>> submitRegisterApi(@Field("token") String token,
                                                  @Field("otp") String otp);




    // end

    // login
    @POST(ServiceUrl.POST_USER_LOGIN)
    Observable<Response<LoginResponse>> loginUser(@Body LoginRequest request);
    // end

    // l?y th�ng tin user
    @GET(ServiceUrl.GET_USER_INFO)
    Observable<Response<UserInfoResponse>> getUserInfo(@Query("MaMayUuid") String MaMayUuid,
                                                       @Query("Tokenhoivien") String Tokenhoivien);
    // end

    //TODO:  point
    @GET(ServiceUrl.GET_HISTORY_POINT)
    Observable<Response<HistoryPointResponse>> getHistoryPoint(
                                                        @Query("Token") String Token,
                                                        @Query("StartIndex") Integer StartIndex,
                                                        @Query("PageSize") Integer PageSize,
                                                        @Query("FromDate") String FromDate,
                                                        @Query("ToDate") String ToDate
                                                        );

    @FormUrlEncoded
    @POST(ServiceUrl.GET_POINT_INFO)
    Observable<Response<PointInfoResponse>> getPointInfo(@Field("token") String token);

    // tặng điểm
    @GET(ServiceUrl.CHECK_CHUC_NANG_TANG_DIEM)
    Observable<Response<CommonApiResult>> checkChucNangTangDiem(@Query("Token") String Token);

    @GET(ServiceUrl.CHECK_DIEU_KIEN_TANG_DIEM)
    Observable<Response<CheckPointResponse>> checkDieuKienTangDiem(@Query("Token") String Token);

    @POST(ServiceUrl.SEND_POINT)
    Observable<Response<CommonApiResult>> sendPoint(@Body DonatePointRequest request);
    // end

     // change info
     @POST(ServiceUrl.CHANGE_PASSWORD)
     Observable<Response<CommonApiResult>> changePassword(@Body ChangePassRequest request);

    @POST(ServiceUrl.CHANGE_EMAIL)
    Observable<Response<CommonApiResult>> changeEmail(@Body ChangeEmailRequest request);

    @POST(ServiceUrl.CHANGE_PHONE_NUMBER)
    Observable<Response<CommonApiResult>> changePhoneNumber(@Body ChangePhoneNumberRequest request);

    @POST(ServiceUrl.CHANGE_USER_INFO)
    Observable<Response<CommonApiResult>> changeUserInfo(@Body ChangeUserInfoRequest request);

    @POST(ServiceUrl.CREATE_OTP)
    Observable<Response<CommonApiResult>> createOTP(@Body CreateOtpRequest request);

    @GET(ServiceUrl.VERIFY_OTP)
    Observable<Response<CommonApiResult>> verifyOTP(@Query("Tokenhoivien") String Tokenhoivien,
                                                    @Query("OTP") String OTP,
                                                    @Query("SoDienThoai") String SoDienThoai
                                                    );
    //end

    // get capcha
    @GET(ServiceUrl.GET_CAPCHA)
    Observable<Response<ResponseBody>> getCapCha();
    // end

    // forgot password
    @POST(ServiceUrl.FORGOT_PASSWORD)
    Observable<Response<CommonApiResult>> forgotPassword(@Body ForgotPasswordRequest request);
    // end

    // notification
    @GET(ServiceUrl.GET_NOTIFICATION)
    Observable<Response<NotificationResponse>> getNotification(@Query("Token") String Token);

    @FormUrlEncoded
    @POST(ServiceUrl.SEND_TOKEN_FIREBASE)
    Observable<Response<CommonApiResult>> sendTokenFirebase(@Field("SoDienThoai") String phone,
                                                            @Field("MaHeDieuHanh") String MaHeDieuHanh,
                                                            @Field("TokenFireBase") String TokenFireBase,
                                                            @Field("MaMayUuid") String MaMayUuid,
                                                            @Field("token") String token);
    // end

    // change image
    @Multipart
    @POST(ServiceUrl.CHANGE_USER_IMAGE)
    Observable<Response<CommonApiResult>> changeAvatar(@Part("Tokenhoivien") String Tokenhoivien,
                                                      @Part MultipartBody.Part file);
    // end

    // get history rank
    @GET(ServiceUrl.GET_HISTORY_RANK)
    Observable<Response<HistoryRankResponse>> getHistoryRank(@Query("Tokenhoivien") String Tokenhoivien);

    // lấy thông tin tài khoản
    @POST(ServiceUrl.GET_ACCOUNT_INFO)
    Observable<Response<AccountInfoResponse>> getAccountInfo(@Body AccountInfoRequest request);
}
