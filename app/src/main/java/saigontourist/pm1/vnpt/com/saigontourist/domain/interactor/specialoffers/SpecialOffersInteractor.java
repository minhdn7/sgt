package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.SearchOnMapSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategorySaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataMemberCardResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataStoreCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseShopSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseSpecialsSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SearchOnMapVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersVpointResult;

/**
 * Created by linhl on 4/13/2018.
 */

public interface SpecialOffersInteractor {
    Observable<SpecialOffersSaigonResult> executeSaigonSpecialOffres(String token, String maUngdung
            , int maTinh, int thuongHieu, int linhVuc, String NoiDungTimKiem, int startIndex, int pageSize);

    Observable<SpecialOffersVpointResult> executeVpointSpecialOffres(int category,  // linh vuc
                                                                     int merchantId, // doanh nghiep
                                                                     int locationId, // thanh pho
                                                                     String searchingTex, // search
                                                                     int page,  // page
                                                                     int limit, // limit
                                                                     int newsTypeId);


    Observable<ResponseBody> getMemberCardCaptcha();

    Observable<DataMemberCardResult> getMemberCardBarQrCode(@Field("token") String token,
                                                            @Field("PIN") int pinCode
    );

    Observable<CommonApiResult> getMemberCardPassCode(@Field("token") String token,
                                                      @Field("MaPin") int pinCode
    );

    Observable<CommonApiResult> getMemberCardAuthentication(@Field("token") String token,
                                                                    @Field("MatKhau") String matKhau,
                                                                    @Field("Captcha") String captcha
    );


    //Vpoint
    Observable<DataCategoryVpointResult> getListFieldsFromService();

    Observable<DataCategoryVpointResult> getListEnterprise();

    Observable<DataCategoryVpointResult> getListCities(int apiId);

    Observable<DataDetailSpecialVpointResult> getDetailSpecialVpoint(int newId);

    Observable<DataDetailSpecialVpointResult.DataListShopByNewResult> getListShopDetailSpecialVpoint(int newId);

    Observable<InfoEnterpriseResult> getDetailInfoEnterpriseVpoint(int newId);

    Observable<DataCurrentOfEnterpriseVpointResult> getListPromotionOfEnterpriseVpoint(int newId);

    Observable<DataStoreCurrentOfEnterpriseVpointResult> getListShopOfEnterpriseVpoint(int newId);

    Observable<SearchOnMapVpointResult> getDataSearchVpointMap(String searchingText, int merchantId, int field,
                                                               int radius, double latitude, double longitude);


    //Saigon
    Observable<DataCategorySaigonResult> getListProvinceCode(String token);

    Observable<DataCategorySaigonResult> getListTradeMark(String token);

    Observable<InfoEnterpriseSaigonResult> getInforEnterpriseTouris(int chuongTrinhUuDaiId);

    Observable<EnterpriseSpecialsSaigonResult> getEnterpriseSpecialsTouris(int chuongTrinhUuDaiId,
                                                                           int startIndex, int pageSize);

    Observable<EnterpriseShopSaigonResult> getEnterpriseShopTouris(int chuongTrinhUuDaiId);

    Observable<DataCategorySaigonResult> getListFieldSTouris(String token);

    Observable<DataDetailSpecialSaigonResult> getDetailSpecialTouris(String token, String maUngDung,
                                                                     int chuongTrinhUuDai);

    Observable<SearchOnMapSaigonResult> getListShopSaiGonOnMap(String token, String maUngDung,
                                                               int fields,int enterprise,int chuongTrinhUuDaiId, String strSearch, int radius, double lat, double lon);

    //


}
