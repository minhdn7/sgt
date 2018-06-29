package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;

/**
 * Created by linhl on 4/20/2018.
 */

public class DataCategorySaigonResult extends CommonApiResult {

    @SerializedName("data")
    @Setter
    @Getter
    private List<DataCategoryResult> listData;

}
