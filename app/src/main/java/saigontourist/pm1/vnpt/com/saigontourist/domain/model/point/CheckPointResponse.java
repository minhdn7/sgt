package saigontourist.pm1.vnpt.com.saigontourist.domain.model.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 27/4/2018.
 */
public class CheckPointResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Getter @Setter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Getter @Setter
    public String errorDesc;

    @SerializedName("MaHoiVienVPoint")
    @Expose
    @Getter @Setter
    public String maHoiVienVPoint;

    @SerializedName("TenHoiVien")
    @Expose
    @Getter @Setter
    public String tenHoiVien;

    @SerializedName("DiemVpoint")
    @Expose
    @Getter @Setter
    public Integer diemVpoint;
}
