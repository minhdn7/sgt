package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiVpointResult;


public class InfoEnterpriseResult extends CommonApiVpointResult {

    @SerializedName("data")
    @Setter
    @Getter
    private InfoEnterpriseModel infoEnterpriseModel;

    public static class InfoEnterpriseModel {
        @SerializedName("avatar")
        @Setter
        @Getter
        private Integer avatar;

        @SerializedName("createdBy")
        @Setter
        @Getter
        private Integer createdBy;

        @SerializedName("createdDate")
        @Setter
        @Getter
        private String createdDate;

        @SerializedName("fieldId")
        @Setter
        @Getter
        private Integer fieldId;

        @SerializedName("id")
        @Setter
        @Getter
        private Integer id;
        @SerializedName("isActive")
        @Setter
        @Getter
        private Integer isActive;
        @SerializedName("link")
        @Setter
        @Getter
        private String link;
        @SerializedName("name")
        @Setter
        @Getter
        private String name;
        @SerializedName("updatedBy")
        @Setter
        @Getter
        private Integer updatedBy;
        @SerializedName("updatedDate")
        @Setter
        @Getter
        private String updatedDate;
        @SerializedName("description")
        @Setter
        @Getter
        private String description;
    }
}
