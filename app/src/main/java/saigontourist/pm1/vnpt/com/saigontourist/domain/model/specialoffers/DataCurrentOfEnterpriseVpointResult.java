package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiVpointResult;

/**
 * Created by linhl on 4/24/2018.
 */

public class DataCurrentOfEnterpriseVpointResult extends CommonApiVpointResult {
    @SerializedName("data")
    @Setter
    @Getter
    private List<SpecialOffersVpointResult.DataOffersVpoint> listPromotionEnterprise;


}
