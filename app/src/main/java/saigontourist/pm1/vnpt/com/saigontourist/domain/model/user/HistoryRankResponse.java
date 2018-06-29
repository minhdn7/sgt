package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class HistoryRankResponse {
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
    public Data data;

    public class Data {
        @SerializedName("LichSu")
        @Expose
        @Getter @Setter
        public List<LichSu> lichSu = null;
    }

    public class LichSu {

        @SerializedName("TenHangHoiVien")
        @Expose
        @Getter @Setter
        public String tenHangHoiVien;

        @SerializedName("ThoiGianXetHang")
        @Expose
        @Getter @Setter
        public String thoiGianXetHang;

        @SerializedName("ThoiGianXetHangTiepTheo")
        @Expose
        @Getter @Setter
        public String thoiGianXetHangTiepTheo;
    }
}
