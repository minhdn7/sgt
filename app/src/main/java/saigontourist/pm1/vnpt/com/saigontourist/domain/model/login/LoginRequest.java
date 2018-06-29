package saigontourist.pm1.vnpt.com.saigontourist.domain.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 18/4/2018.
 */
public class LoginRequest {
    @SerializedName("Para1")
    @Expose
    @Setter @Getter
    public String para1;

    @SerializedName("Para2")
    @Expose
    @Setter @Getter
    public String para2;

    @SerializedName("MaMayUuId")
    @Expose
    @Setter @Getter
    public String maMayUuId;

    public LoginRequest(String para1, String para2, String maMayUuId) {
        this.para1 = para1;
        this.para2 = para2;
        this.maMayUuId = maMayUuId;
    }
}
