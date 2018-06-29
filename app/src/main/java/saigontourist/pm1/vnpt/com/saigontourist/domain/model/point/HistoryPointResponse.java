package saigontourist.pm1.vnpt.com.saigontourist.domain.model.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 23/4/2018.
 */
public class HistoryPointResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Getter @Setter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Getter @Setter
    public String errorDesc;

    @SerializedName("data")
    @Expose
    @Getter @Setter
    public List<Datum> data = null;

    public class Datum {

        @SerializedName("Ngay")
        @Expose
        @Getter @Setter
        public String ngay;

        @SerializedName("LyDo")
        @Expose
        @Getter @Setter
        public String lyDo;

        @SerializedName("Diem")
        @Expose
        @Getter @Setter
        public Integer diem;

        @SerializedName("TenLoaiDiem")
        @Expose
        @Getter @Setter
        public String tenLoaiDiem;

        @SerializedName("DiaDiemGiaoDich")
        @Expose
        @Getter @Setter
        public String diaDiemGiaoDich;
    }
}
