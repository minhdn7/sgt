package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by THAOPX on 4/27/2017.
 */

public class SpecialOffersVpointResult {
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

    @SerializedName("errorCode")
    @Setter
    @Getter
    private int errorCode;

    @SerializedName("data")
    @Setter
    @Getter
    private List<DataOffersVpoint> dataDanhSachTinTucResponeList;

    public class DataOffersVpoint extends SpecialOffersObject implements Serializable {
        @SerializedName("avatar")
        @Setter
        @Getter
        private Integer avatar;

        @SerializedName("categoryId")
        @Setter
        @Getter
        private Integer categoryId;

        @SerializedName("categoryName")
        @Setter
        @Getter
        private String categoryName;

        @SerializedName("createdDate")
        @Setter
        @Getter
        private String createdDate;

        @SerializedName("excerpt")
        @Setter
        @Getter
        private String excerpt;

        @SerializedName("id")
        @Setter
        @Getter
        private Integer id;

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

        @SerializedName("newsTypeName")
        @Setter
        @Getter
        private String newsTypeName;

        @SerializedName("title")
        @Setter
        @Getter
        private String title;

        @SerializedName("shareLink")
        @Setter
        @Getter
        private String shareLink;

        @SerializedName("allocation")
        @Setter
        @Getter
        private String allocation;


        public DataOffersVpoint(int typeView) {
            super(typeView);
        }
    }
}
