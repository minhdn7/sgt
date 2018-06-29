package saigontourist.pm1.vnpt.com.saigontourist.domain.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 18/4/2018.
 */
public class LoginResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Getter @Setter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Getter @Setter
    public String errorDesc;

    @SerializedName("Token")
    @Expose
    @Getter @Setter
    public String token;
}
