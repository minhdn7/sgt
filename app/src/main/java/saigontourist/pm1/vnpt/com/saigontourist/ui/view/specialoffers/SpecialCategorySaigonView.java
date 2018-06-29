package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/20/2018.
 */

public interface SpecialCategorySaigonView extends View {
    void onGetListProvinceCodeSuccses(List<DataCategoryResult> listPovince);

    void onGetListTradeMarkSuccses(List<DataCategoryResult> listEnterprise);
    void onGetListFieldSTourisSuccses(List<DataCategoryResult> listFields);

    void onGetListProvinceCodeFailed(String message);

    void onGetListTradeMarkFailed(String message);
    void onGetListFieldSTourisFailed(String message);

    void onGetListError(Throwable e);

}
