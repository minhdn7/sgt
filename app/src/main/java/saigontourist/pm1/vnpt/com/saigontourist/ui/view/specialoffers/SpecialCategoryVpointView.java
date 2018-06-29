package saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers;

import java.util.List;

import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by linhl on 4/20/2018.
 */

public interface SpecialCategoryVpointView extends View{
    void onGetListFieldsSuccses(List<DataCategoryResult> listFields);

    void onGetListEnterpriseSuccses(List<DataCategoryResult> listEnterprise);
    void onGetListCitiesSuccses(List<DataCategoryResult> listCities);

    void onGetListFieldsFailed(String message);

    void onGetListEnterpriseFailed(String message);
    void onGetListCitiesFailed(String message);

    void onGetListError(Throwable e);
}
