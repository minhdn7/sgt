package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
/**
 * Created by linhl on 4/13/2018.
 */

public class RegisterUserApiResult extends CommonApiResult {
    @SerializedName("token")
    @Setter @Getter
    public String token;

}
