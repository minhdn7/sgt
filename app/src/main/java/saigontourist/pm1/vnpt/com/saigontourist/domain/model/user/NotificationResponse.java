package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 26/4/2018.
 */
public class NotificationResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Getter @Setter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Getter @Setter
    public String errorDesc;

    @SerializedName("Result")
    @Expose
    @Getter @Setter
    public List<Result> result = null;

    public class Result {
        @SerializedName("Title")
        @Expose
        @Getter @Setter
        public String title;

        @SerializedName("Message")
        @Expose
        @Getter @Setter
        public String message;

        @SerializedName("URL")
        @Expose
        @Getter @Setter
        public String uRL;

        @SerializedName("NoiDungChiTiet")
        @Expose
        @Getter @Setter
        public String noiDungChiTiet;

        @SerializedName("ChuongTrinhUuDaiId")
        @Expose
        @Getter @Setter
        public Integer chuongTrinhUuDaiId;

        @SerializedName("NgayGioPush")
        @Expose
        @Getter @Setter
        public String NgayGioPush;

    }
}
