package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 18/4/2018.
 */
public class UserInfoResponse {

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
        @SerializedName("thongtin")
        @Expose
        @Getter @Setter
        public List<Thongtin> thongtin = null;

        @SerializedName("diem")
        @Expose
        @Getter @Setter
        public Diem diem;

        @SerializedName("Ds_CTUDRated")
        @Expose
        @Getter @Setter
        public List<Object> dsCTUDRated = null;

        @SerializedName("Ds_CTUULiked")
        @Expose
        @Getter @Setter
        public List<Object> dsCTUULiked = null;
    }


    public class Diem {
        @SerializedName("tendiemvpoint")
        @Expose
        @Getter @Setter
        public String tendiemvpoint;

        @SerializedName("diemvpoint")
        @Expose
        @Getter @Setter
        public String diemvpoint;

        @SerializedName("tendiemvinaphone")
        @Expose
        @Getter @Setter
        public String tendiemvinaphone;

        @SerializedName("diemvinaphone")
        @Expose
        @Getter @Setter
        public String diemvinaphone;
    }

    public class Thongtin {
        @SerializedName("MaHoiVien")
        @Expose
        @Getter @Setter
        public String maHoiVien;

        @SerializedName("TenDangNhap")
        @Expose
        @Getter @Setter
        public String tenDangNhap;

        @SerializedName("TenHoiVien")
        @Expose
        @Getter @Setter
        public String tenHoiVien;

        @SerializedName("AnhDaiDien")
        @Expose
        @Getter @Setter
        public Object anhDaiDien;

        @SerializedName("GioiTinh")
        @Expose
        @Getter @Setter
        public Integer gioiTinh;

        @SerializedName("TenHangHoiVien")
        @Expose
        @Getter @Setter
        public String tenHangHoiVien;

        @SerializedName("Tinh")
        @Expose
        @Getter @Setter
        public String Tinh;

        @SerializedName("Quan")
        @Expose
        @Getter @Setter
        public String Quan;

        @SerializedName("Phuong")
        @Expose
        @Getter @Setter
        public String Phuong;

        @SerializedName("SoDienThoai")
        @Expose
        @Getter @Setter
        public String soDienThoai;

        @SerializedName("Email")
        @Expose
        @Getter @Setter
        public String email;

        @SerializedName("DiaChi")
        @Expose
        @Getter @Setter
        public String diaChi;

        @SerializedName("MaHoiVienVPoint")
        @Expose
        @Getter @Setter
        public String maHoiVienVPoint;

        @SerializedName("NgaySinh")
        @Expose
        @Getter @Setter
        public String ngaySinh;

        @SerializedName("NgheNghiep")
        @Expose
        @Getter @Setter
        public String ngheNghiep;

        @SerializedName("SoDinhDanh")
        @Expose
        @Getter @Setter
        public String soDinhDanh;

        @SerializedName("QuocTich")
        @Expose
        @Getter @Setter
        public String quocTich;

        @SerializedName("NgayCapGiayToDinhDanh")
        @Expose
        @Getter @Setter
        public String ngayCapGiayToDinhDanh;

        @SerializedName("NoiCapGiayToDinhDanh")
        @Expose
        @Getter @Setter
        public String noiCapGiayToDinhDanh;
    }
}
