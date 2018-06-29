package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 4/5/2018.
 */
public class ForgotPasswordRequest {
    @SerializedName("ToKenNhaPhatTrien")
    @Expose
    @Getter @Setter
    public String tokenNhaPhatTrien;

    @SerializedName("TaiKhoan")
    @Expose
    @Getter @Setter
    public String taiKhoan;

    @SerializedName("Captcha")
    @Expose
    @Getter @Setter
    public String Captcha;


    public ForgotPasswordRequest(String tokenNhaPhatTrien, String taiKhoan, String captcha) {
        this.tokenNhaPhatTrien = tokenNhaPhatTrien;
        this.taiKhoan = taiKhoan;
        Captcha = captcha;
    }
}
