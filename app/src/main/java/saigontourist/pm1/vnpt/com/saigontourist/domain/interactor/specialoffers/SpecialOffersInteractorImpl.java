package saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers;

import javax.inject.Inject;

import okhttp3.ResponseBody;
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
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.SpecialOffersSaigonApi;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.SpecialOffersVpointApi;

/**
 * Created by linhl on 4/13/2018.
 */

public class SpecialOffersInteractorImpl implements SpecialOffersInteractor {
    @Inject
    SpecialOffersSaigonApi specialOffersSaigonApi;
    @Inject
    SpecialOffersVpointApi specialOffersVpointApi;

    public SpecialOffersInteractorImpl() {
    }

    @Override
    public Observable<SpecialOffersSaigonResult> executeSaigonSpecialOffres(String token, String maUngdung, int maTinh,
                                                                            int thuongHieu, int linhVuc, String noiDungTimKiem, int startIndex, int pageSize) {

        return specialOffersSaigonApi.getListSaigonSpecialOffres(token, maUngdung, maTinh, thuongHieu, linhVuc, noiDungTimKiem, startIndex, pageSize);
    }

    @Override
    public Observable<SpecialOffersVpointResult> executeVpointSpecialOffres(int category, int merchantId,
                                                                            int locationId, String searchingTex,
                                                                            int page, int limit, int newsTypeId) {
        return specialOffersVpointApi.getListVpointSpecialOffres(category, merchantId, locationId, searchingTex,
                page, limit, newsTypeId);
    }

    @Override
    public Observable<ResponseBody> getMemberCardCaptcha() {
        return specialOffersSaigonApi.getMemberCardCaptcha();
    }

    @Override
    public Observable<DataMemberCardResult> getMemberCardBarQrCode(String token, int pinCode) {
        return specialOffersSaigonApi.getMemberCardBarQrCode(token,pinCode);
    }

    @Override
    public Observable<CommonApiResult> getMemberCardPassCode(String token, int maPin) {
        return specialOffersSaigonApi.getMemberCardPassCode(token,maPin);
    }

    @Override
    public Observable<CommonApiResult> getMemberCardAuthentication(String token, String matKhau, String captcha) {
        return specialOffersSaigonApi.getMemberCardAuthentication(token,matKhau,captcha);
    }

    @Override
    public Observable<DataCategoryVpointResult> getListFieldsFromService() {
        return specialOffersVpointApi.getListFieldsFromService();
    }

    @Override
    public Observable<DataCategoryVpointResult> getListEnterprise() {
        return specialOffersVpointApi.getListEnterpriseFromService();
    }

    @Override
    public Observable<DataCategoryVpointResult> getListCities(int apiId) {
        return specialOffersVpointApi.getListCitiesFromService(apiId);
    }

    @Override
    public Observable<DataDetailSpecialVpointResult> getDetailSpecialVpoint(int newId) {
        return specialOffersVpointApi.getDetailSpecialVpoint(newId);
    }

    @Override
    public Observable<DataDetailSpecialVpointResult.DataListShopByNewResult> getListShopDetailSpecialVpoint(int newId) {
        return specialOffersVpointApi.getListShopDetailSpecialVpoint(newId);
    }

    @Override
    public Observable<InfoEnterpriseResult> getDetailInfoEnterpriseVpoint(int newId) {
        return specialOffersVpointApi.getDetailInfoEnterprise(newId);
    }

    @Override
    public Observable<DataCurrentOfEnterpriseVpointResult> getListPromotionOfEnterpriseVpoint(int newId) {
        return specialOffersVpointApi.getListPromotionOfEnterprise(newId);
    }

    @Override
    public Observable<DataStoreCurrentOfEnterpriseVpointResult> getListShopOfEnterpriseVpoint(int newId) {
        return specialOffersVpointApi.getListShopOfEnterprise(newId);
    }

    @Override
    public Observable<SearchOnMapVpointResult> getDataSearchVpointMap(String searchingText, int merchantId, int field, int radius, double latitude, double longitude) {
        return specialOffersVpointApi.getDataSearchVpointMap(searchingText, merchantId, field, radius, latitude, longitude);
    }

    @Override
    public Observable<DataCategorySaigonResult> getListProvinceCode(String token) {
        return specialOffersSaigonApi.getListProvinceCode(token);
    }

    @Override
    public Observable<DataCategorySaigonResult> getListTradeMark(String token) {
        return specialOffersSaigonApi.getListTradeMark(token);
    }

    @Override
    public Observable<InfoEnterpriseSaigonResult> getInforEnterpriseTouris(int chuongTrinhUuDaiId) {
        return specialOffersSaigonApi.getInforEnterpriseTouris(chuongTrinhUuDaiId);
    }

    @Override
    public Observable<EnterpriseSpecialsSaigonResult> getEnterpriseSpecialsTouris(int chuongTrinhUuDaiId,
                                                                                  int startIndex, int pageSize) {
        return specialOffersSaigonApi.getEnterpriseSpecialsTouris(chuongTrinhUuDaiId, startIndex, pageSize);
    }

    @Override
    public Observable<EnterpriseShopSaigonResult> getEnterpriseShopTouris(int chuongTrinhUuDaiId) {
        return specialOffersSaigonApi.getEnterpriseShopTouris(chuongTrinhUuDaiId);
    }

    @Override
    public Observable<DataCategorySaigonResult> getListFieldSTouris(String token) {
        return specialOffersSaigonApi.getListFieldSTouris(token);
    }

    @Override
    public Observable<DataDetailSpecialSaigonResult> getDetailSpecialTouris(String token, String maUngDung, int chuongTrinhUuDai) {
        return specialOffersSaigonApi.getDetailSpecialTouris(token, maUngDung, chuongTrinhUuDai);
    }

    @Override
    public Observable<SearchOnMapSaigonResult> getListShopSaiGonOnMap(String token, String maUngDung, int fields,int enterprise,int chuongTrinhUuDaiId,
                                                                      String strSearch,
                                                                      int radius, double lat, double lon) {
        return specialOffersSaigonApi.getListShopOnMap(token, maUngDung, fields, enterprise,chuongTrinhUuDaiId,strSearch, radius, lat, lon);
    }
}
