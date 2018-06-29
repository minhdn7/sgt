package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiVpointResult;

/**
 * Created by THAOPX on 6/5/2017.
 */

public class SearchOnMapVpointResult extends CommonApiVpointResult {

    @SerializedName("data")
    @Setter
    @Getter
    private List<ItemSearchOnMap> listDataMapResponses;

    public static class ItemSearchOnMap {
        @SerializedName("address")
        @Setter
        @Getter
        private String address;
        @SerializedName("commune")
        @Setter
        @Getter
        private Integer commune;
        @SerializedName("createdBy")
        @Setter
        @Getter
        private String createdBy;
        @SerializedName("createdDate")
        @Setter
        @Getter
        private String createdDate;
        @SerializedName("district")
        @Setter
        @Getter
        private Integer district;
        @SerializedName("id")
        @Setter
        @Getter
        private Integer id;
        @SerializedName("latitude")
        @Setter
        @Getter
        private Double latitude;
        @SerializedName("longitude")
        @Setter
        @Getter
        private Double longitude;
        @SerializedName("merchantId")
        @Setter
        @Getter
        private Integer merchantId;
        @SerializedName("name")
        @Setter
        @Getter
        private String name;
        @SerializedName("phone")
        @Setter
        @Getter
        private String phone;
        @SerializedName("provine")
        @Setter
        @Getter
        private Integer provine;
        @SerializedName("merchant")
        @Setter
        @Getter
        private String merchant;
        @SerializedName("avatarId")
        @Setter
        @Getter
        private Integer avatarId;
        @SerializedName("fieldId")
        @Setter
        @Getter
        private Integer fieldId;
    }
}
