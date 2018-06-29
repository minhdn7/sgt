package saigontourist.pm1.vnpt.com.saigontourist.domain.model.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 20/4/2018.
 */
public class PointInfoResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Setter @Getter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Setter @Getter
    public String errorDesc;

    @SerializedName("DiemVpoint")
    @Expose
    @Setter @Getter
    public String diemVpoint;

    @SerializedName("ThongTinDiem")
    @Expose
    @Setter @Getter
    public List<ThongTinDiem> thongTinDiem = null;

    public static class ThongTinDiem {
        @SerializedName("Diem")
        @Expose
        @Setter @Getter
        public Integer diem;

        @SerializedName("PhamViSuDung")
        @Expose
        @Setter @Getter
        public String phamViSuDung;

        @SerializedName("LoaiDiem")
        @Expose
        @Setter @Getter
        public String loaiDiem;

        @SerializedName("HanSuDung")
        @Expose
        @Setter @Getter
        public String hanSuDung;

        public ThongTinDiem(Integer diem, String phamViSuDung, String loaiDiem, String hanSuDung) {
            this.diem = diem;
            this.phamViSuDung = phamViSuDung;
            this.loaiDiem = loaiDiem;
            this.hanSuDung = hanSuDung;
        }
    }
}
