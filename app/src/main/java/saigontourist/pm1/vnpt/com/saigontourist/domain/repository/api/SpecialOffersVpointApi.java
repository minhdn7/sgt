package saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataDetailSpecialVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataStoreCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SearchOnMapVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.ServiceUrl;

/**
 * Created by linhl on 4/18/2018.
 */

public interface SpecialOffersVpointApi {
    @GET("/mypage.api/rest/news")
    Observable<SpecialOffersVpointResult> getListVpointSpecialOffres(@Query("category") int category,  // linh vuc
                                                                     @Query("merchantId") int merchantId, // doanh nghiep
                                                                     @Query("locationId") int locationId, // thanh pho
                                                                     @Query("searchingText") String searchingTex, // search
                                                                     @Query("page") int page,  // page
                                                                     @Query("limit") int limit, // limit
                                                                     @Query("newsTypeId") int newsTypeId //

    );

    @GET(ServiceUrl.GET_LIST_ENTERPRISE_URL)
    Observable<DataCategoryVpointResult> getListEnterpriseFromService();

    @GET(ServiceUrl.GET_LIST_FIELDS_URL)
    Observable<DataCategoryVpointResult> getListFieldsFromService();
    @GET(ServiceUrl.GET_LIST_CITIES_URL)
    Observable<DataCategoryVpointResult> getListCitiesFromService(
            @Query("apiId") Integer apiId
    );

    @GET(ServiceUrl.GET_DETAIL_NEWS_URL)
    Observable<DataDetailSpecialVpointResult> getDetailSpecialVpoint(
            @Path("newsId") int newId
    );
    @GET(ServiceUrl.GET_LIST_SHOP_BY_NEW_URL)
    Observable<DataDetailSpecialVpointResult.DataListShopByNewResult> getListShopDetailSpecialVpoint(
            @Path("newsId") int newId
    );

    @GET(ServiceUrl.GET_DETAIL_INFO_ENTERPRISE_VPOINT_URL)
    Observable<InfoEnterpriseResult> getDetailInfoEnterprise(@Path("id") int newId);
    @GET(ServiceUrl.GET_LIST_NEWS_PROMOTION_OF_ENTERPRISE_VPOINT_URL)
    Observable<DataCurrentOfEnterpriseVpointResult> getListPromotionOfEnterprise(@Path("id") int newId);
    @GET(ServiceUrl.GET_LIST_SHOP_OF_ENTERPRISE_VPOINT_URL)
    Observable<DataStoreCurrentOfEnterpriseVpointResult> getListShopOfEnterprise(@Path("id") int newId);

    @GET(ServiceUrl.LIST_SHOP_ON_MAP_VPOINT_URL)
    Observable<SearchOnMapVpointResult> getDataSearchVpointMap(@Query("searchingText") String searchingText,
                                                         @Query("merchantId") Integer merchantId,
                                                         @Query("field") Integer field,
                                                         @Query("radius") Integer radius,
                                                         @Query("latitude") Double latitude,
                                                         @Query("longitude") Double longitude);
}
