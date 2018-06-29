package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by THAOPX on 5/10/2017.
 */

public class DataDetailSpecialVpointResult {
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
    private List<DetailNewResponse> dataDetailNewResponses;


    public static class DataListShopByNewResult {
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
        private List<DataShop> dataListShopByNewResponses;

        public static class DataShop extends DataShopDetail{
            @SerializedName("address")
            @Setter
            @Getter
            private String address;
            @SerializedName("commune")
            @Setter
            @Getter
            private Integer commune;
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
        }
    }
    public class DetailNewResponse {
        @SerializedName("avatar")
        @Setter
        @Getter
        private Integer avatar;

        @SerializedName("allocation")
        @Setter
        @Getter
        private String allocation;

        @SerializedName("applyText")
        @Setter
        @Getter
        private String applyText;

        @SerializedName("applyType")
        @Setter
        @Getter
        private Integer applyType;

        @SerializedName("categoryId")
        @Setter
        @Getter
        private Integer categoryId;

        @SerializedName("categoryName")
        @Setter
        @Getter
        private String categoryName;

        @SerializedName("commentStatus")
        @Setter
        @Getter
        private Integer commentStatus;

        @SerializedName("content")
        @Setter
        @Getter
        private String content;

        @SerializedName("createdBy")
        @Setter
        @Getter
        private String createdBy;

        @SerializedName("createdDate")
        @Setter
        @Getter
        private Date createdDate;

        @SerializedName("excerpt")
        @Setter
        @Getter
        private String excerpt;

        @SerializedName("highlights")
        @Setter
        @Getter
        private Integer highlights;

        @SerializedName("id")
        @Setter
        @Getter
        private Integer id;

        @SerializedName("isActive")
        @Setter
        @Getter
        private Integer isActive;

        @SerializedName("merchantId")
        @Setter
        @Getter
        private Integer merchantId;

        @SerializedName("merchantName")
        @Setter
        @Getter
        private String merchantName;

        @SerializedName("newsTypeId")
        @Setter
        @Getter
        private Integer newsTypeId;

        @SerializedName("status")
        @Setter
        @Getter
        private Integer status;

        @SerializedName("title")
        @Setter
        @Getter
        private String title;

        @SerializedName("shareLink")
        @Setter
        @Getter
        private String shareLink;

        @SerializedName("htmlContent")
        @Setter
        @Getter
        private String htmlContent;

        @SerializedName("fieldId")
        @Setter
        @Getter
        private Integer fieldId;
    }

}
