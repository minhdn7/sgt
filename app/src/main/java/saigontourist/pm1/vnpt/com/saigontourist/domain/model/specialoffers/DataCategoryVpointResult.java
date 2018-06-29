package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/20/2018.
 */

public class DataCategoryVpointResult {

    @SerializedName("type")
    @Setter
    @Getter
    private String type;

    @SerializedName("message")
    @Setter
    @Getter
    private String message;

    @SerializedName("status")
    @Setter
    @Getter
    private Integer status;

    @SerializedName("total")
    @Setter
    @Getter
    private Integer total;
    @SerializedName("data")
    @Setter
    @Getter
    private List<DataCategoryResult> dataListResult;


}
