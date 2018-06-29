package saigontourist.pm1.vnpt.com.saigontourist.domain.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/24/2018.
 */

public class CommonApiVpointResult {
    @SerializedName("type")
    @Setter
    @Getter
    private String type;

    @SerializedName("message")
    @Setter
    @Getter
    private String message;

    @SerializedName("status")
    @Setter
    @Getter
    private Integer status;

    @SerializedName("total")
    @Setter
    @Getter
    private Integer total;
}
