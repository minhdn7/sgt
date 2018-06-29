package saigontourist.pm1.vnpt.com.saigontourist.domain.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/13/2018.
 */

public class CommonApiResult {
    @SerializedName("ErrorDesc")
    @Setter
    @Getter
    public String errorDesc;

    @SerializedName("ErrorCode")
    @Setter @Getter
    public String errorCode;
}
