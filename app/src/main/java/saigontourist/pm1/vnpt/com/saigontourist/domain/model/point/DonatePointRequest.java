package saigontourist.pm1.vnpt.com.saigontourist.domain.model.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 27/4/2018.
 */
public class DonatePointRequest implements Serializable {
    @SerializedName("Token")
    @Expose
    @Getter @Setter
    public String token;

    @SerializedName("SoDienThoaiEmail")
    @Expose
    @Getter @Setter
    public String soDienThoaiEmail;

    @SerializedName("ThucHienTangDiem")
    @Expose
    @Getter @Setter
    public String thucHienTangDiem;

    @SerializedName("SoDiemTang")
    @Expose
    @Getter @Setter
    public Integer soDiemTang;

    @SerializedName("MaXacThuc")
    @Expose
    @Getter @Setter
    public String maXacThuc;

    @SerializedName("MaMayUuid")
    @Expose
    @Getter @Setter
    public String maMayUuid;
}
