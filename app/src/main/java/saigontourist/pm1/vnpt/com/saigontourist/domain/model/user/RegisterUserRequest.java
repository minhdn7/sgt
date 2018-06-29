package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/13/2018.
 */

public class RegisterUserRequest {
    @SerializedName("HoTen")
    @Setter @Getter
    public String hoTen;

    @SerializedName("TenDangNhap")
    @Setter @Getter
    public String tenDangNhap;

    @SerializedName("MatKhau")
    @Setter @Getter
    public String matKhau;

    @SerializedName("Email")
    @Setter @Getter
    public String email;

    @SerializedName("SoDienThoai")
    @Setter @Getter
    public String soDienThoai;

    @SerializedName("NgaySinh")
    @Setter @Getter
    public String ngaySinh;

    @SerializedName("DiaChi")
    @Setter @Getter
    public String diaChi;

    @SerializedName("GioiTinh")
    @Setter @Getter
    public Integer gioiTinh;

}
