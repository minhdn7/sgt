package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangePassRequest {
    @SerializedName("Tokenhoivien")
    @Expose
    @Setter @Getter
    public String tokenhoivien;

    @SerializedName("MatKhauHientai")
    @Expose
    @Setter @Getter
    public String matKhauHientai;

    @SerializedName("MatKhauMoi")
    @Expose
    @Setter @Getter
    public String matKhauMoi;

    @SerializedName("XacNhanMatKhauMoi")
    @Expose
    @Setter @Getter
    public String xacNhanMatKhauMoi;
}
