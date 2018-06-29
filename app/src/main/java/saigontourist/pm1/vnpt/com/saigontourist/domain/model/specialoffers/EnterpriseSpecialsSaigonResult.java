package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;

/**
 * Created by linhl on 5/3/2018.
 */

public class EnterpriseSpecialsSaigonResult extends CommonApiResult {
    @SerializedName("Data")
    @Setter
    @Getter
    private List<SpecialOffersSaigonResult.SpecialOffer> listData;
}
