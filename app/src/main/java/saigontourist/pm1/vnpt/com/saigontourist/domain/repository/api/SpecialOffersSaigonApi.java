package saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.SearchOnMapSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategorySaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataMemberCardResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseShopSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseSpecialsSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.ServiceUrl;

/**
 * Created by linhl on 4/18/2018.
 */

public interface SpecialOffersSaigonApi {
    @GET("/Api_TimKiemChuongTrinhUuDai")
    Observable<SpecialOffersSaigonResult> getListSaigonSpecialOffres(@Query("Token") String token,
                                                                     @Query("MaUngDung") String maUngdung,
                                                                     @Query("MaTinh") int maTinh,
                                                                     @Query("ThuongHieu") int thuongHieu,
                                                                     @Query("LinhVuc") int linhVuc,
                                                                     @Query("NoiDungTimKiem") String NoiDungTimKiem,
                                                                     @Query("StartIndex") int startIndex,
                                                                     @Query("PageSize") int pageSize);

    @GET(ServiceUrl.GET_LIST_PROVINCECODE)
    Observable<DataCategorySaigonResult> getListProvinceCode(@Query("Token") String token);

    @GET(ServiceUrl.GET_LIST_TRADETMARK)
    Observable<DataCategorySaigonResult> getListTradeMark(@Query("Token") String token);

    @GET(ServiceUrl.GET_LIST_FIELDSTOURIS)
    Observable<DataCategorySaigonResult> getListFieldSTouris(@Query("Token") String token
    );

    @FormUrlEncoded
    @POST(ServiceUrl.GET_INFO_ENTERPRISE_SAIGON_URL)
    Observable<InfoEnterpriseSaigonResult> getInforEnterpriseTouris(@Field("ChuongTrinhUuDaiId") int chuongTrinhUuDaiId
    );

    @FormUrlEncoded
    @POST(ServiceUrl.GET_ENTERPRISE_SPECIAL_SAIGON_URL)
    Observable<EnterpriseSpecialsSaigonResult> getEnterpriseSpecialsTouris(@Field("ChuongTrinhUuDaiId") int chuongTrinhUuDaiId,
                                                                           @Field("StartIndex") int startIndex,
                                                                           @Field("PageSize") int pageSize

    );

    @FormUrlEncoded
    @POST(ServiceUrl.GET_ENTERPRISE_SHOP_SAIGON_URL)
    Observable<EnterpriseShopSaigonResult> getEnterpriseShopTouris(@Field("ChuongTrinhUuDaiId") int chuongTrinhUuDaiId

    );

    @GET(ServiceUrl.GET_DETAIL_SPECIAL_OFFERS)
    Observable<DataDetailSpecialSaigonResult> getDetailSpecialTouris(@Query("Token") String token,
                                                                     @Query("MaUngDung") String maUngDung,
                                                                     @Query("ChuongTrinhUuDaiId") int chuongTrinhUuDai
    );

    @GET(ServiceUrl.GET_SHOP_ON_MAP_SAIGON_URL)
    Observable<SearchOnMapSaigonResult> getListShopOnMap(@Query("Token") String token,
                                                         @Query("MaUngDung") String maUngDung,
                                                         @Query("LinhVuc") int fields, @Query("DoanhNghiep") int enterprise, @Query("ChuongTrinhUuDaiId") int chuongTrinhUuDaiId,
                                                         @Query("NoiDungTimKiem") String strSearch,
                                                         @Query("BanKinh") int banKinh, @Query("KinhDo") double lat, @Query("ViDo") double lon
    );

    @GET(ServiceUrl.GET_CARD_MEMBER_CAPTCHA)
    Observable<ResponseBody> getMemberCardCaptcha();

    @FormUrlEncoded
    @POST(ServiceUrl.GET_CARD_MEMBER_BARCODE_QRCODE)
    Observable<DataMemberCardResult> getMemberCardBarQrCode(@Field("token") String token,
                                                            @Field("PIN") int pinCode
    );

    @FormUrlEncoded
    @POST(ServiceUrl.GET_CARD_MEMBER_CREATE_PASS_CODE)
    Observable<CommonApiResult> getMemberCardPassCode(@Field("token") String token,
                                                      @Field("MaPin") int pinCode
    );

    @FormUrlEncoded
    @POST(ServiceUrl.GET_CARD_MEMBER_AUTHENTICATION)
    Observable<CommonApiResult> getMemberCardAuthentication(@Field("token") String token,
                                                            @Field("MatKhau") String matKhau,
                                                            @Field("Captcha") String captcha
    );


}