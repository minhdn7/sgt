package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/19/2018.
 */

public class GetTokenDevRequest {
    @SerializedName("Para1")
    @Setter
    @Getter
    public String para1;
    @SerializedName("Para2")
    @Setter
    @Getter
    public String para2;

    public GetTokenDevRequest(String para1, String para2) {
        this.para1 = para1;
        this.para2 = para2;
    }
}
