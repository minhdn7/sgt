package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class AccountInfoResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Setter @Getter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Getter @Setter
    public String errorDesc;

    @SerializedName("Data")
    @Expose
    @Setter @Getter
    public Data data;

    public class Data {
        @SerializedName("DiemVpoint")
        @Expose
        @Setter @Getter
        public String diemVpoint;

        @SerializedName("Diemtichluy")
        @Expose
        @Setter @Getter
        public Integer diemtichluy;

        @SerializedName("Diemconthieu")
        @Expose
        @Setter @Getter
        public String diemconthieu;

        @SerializedName("Ngayxethang")
        @Expose
        @Setter @Getter
        public String ngayxethang;

        @SerializedName("TenHangHoiVien")
        @Expose
        @Setter @Getter
        public String tenHangHoiVien;

        @SerializedName("TenHoiVien")
        @Expose
        @Setter @Getter
        public String tenHoiVien;

        @SerializedName("TuNgay")
        @Expose
        @Setter @Getter
        public String tuNgay;

        @SerializedName("DenNgay")
        @Expose
        @Setter @Getter
        public String denNgay;

        @SerializedName("ThongTinDiem")
        @Expose
        @Setter @Getter
        public List<ThongTinDiem> thongTinDiem = null;

        @SerializedName("ThoiGianXetHang")
        @Expose
        @Setter @Getter
        public String thoiGianXetHang;

        @SerializedName("ThoiGianXetHangTiep")
        @Expose
        @Setter @Getter
        public String thoiGianXetHangTiep;

        @SerializedName("DiemXetHang")
        @Expose
        @Setter @Getter
        public Integer diemXetHang;

        @SerializedName("AnhDaiDien")
        @Expose
        @Setter @Getter
        public String anhDaiDien;

        @SerializedName("DieuKienDuyTriHang")
        @Expose
        @Setter @Getter
        public String dieuKienDuyTriHang;

        @SerializedName("KyTu")
        @Expose
        @Setter @Getter
        public String KyTu;

        @SerializedName("KyDen")
        @Expose
        @Setter @Getter
        public String KyDen;
    }

    public class ThongTinDiem{
        @SerializedName("STT")
        @Expose
        @Setter @Getter
        public Integer sTT;

        @SerializedName("PhamVi")
        @Expose
        @Setter @Getter
        public String phamVi;

        @SerializedName("SoDiem")
        @Expose
        @Setter @Getter
        public String soDiem;

        @SerializedName("HanSuDung")
        @Expose
        @Setter @Getter
        public String hanSuDung;
    }
}
