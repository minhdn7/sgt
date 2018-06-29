package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangeUserInfoRequest {
    @SerializedName("MaMayUuid")
    @Expose
    @Getter @Setter
    public String maMayUuid;

    @SerializedName("Tokenhoivien")
    @Expose
    @Getter @Setter
    public String tokenhoivien;

    @SerializedName("tendangnhap")
    @Expose
    @Getter @Setter
    public String tendangnhap;

    @SerializedName("hoten")
    @Expose
    @Getter @Setter
    public String hoten;

    @SerializedName("ngaysinh")
    @Expose
    @Getter @Setter
    public String ngaysinh;

    @SerializedName("sex")
    @Expose
    @Getter @Setter
    public Integer sex;

    @SerializedName("tinh")
    @Expose
    @Getter @Setter
    public String tinh;

    @SerializedName("quanhuyen")
    @Expose
    @Getter @Setter
    public String quanhuyen;

    @SerializedName("phuongxa")
    @Expose
    @Getter @Setter
    public String phuongxa;

    @SerializedName("diachi")
    @Expose
    @Getter @Setter
    public String diachi;

    @SerializedName("sodinhdanh")
    @Expose
    @Getter @Setter
    public String sodinhdanh;

    @SerializedName("ngaycapgiaytodinhdanh")
    @Expose
    @Getter @Setter
    public String ngaycapgiaytodinhdanh;

    @SerializedName("noicapgiaytodinhdanh")
    @Expose
    @Getter @Setter
    public String noicapgiaytodinhdanh;

    @SerializedName("nghenghiep")
    @Expose
    @Getter @Setter
    public String nghenghiep;
}
