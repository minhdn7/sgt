package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 19/4/2018.
 */
public class RegisterUserResponse {
    @SerializedName("ErrorDesc")
    @Setter @Getter
    public String errorDesc;

    @SerializedName("ErrorCode")
    @Setter @Getter
    public String errorCode;

    @SerializedName("token")
    @Setter @Getter
    public String token;
}
