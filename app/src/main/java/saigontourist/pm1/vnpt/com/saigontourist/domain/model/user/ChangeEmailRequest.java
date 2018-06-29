package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangeEmailRequest {
    @SerializedName("MaMayUuid")
    @Expose
    @Setter @Getter
    public String maMayUuid;

    @SerializedName("Tokenhoivien")
    @Expose
    @Setter @Getter
    public String tokenhoivien;

    @SerializedName("email")
    @Expose
    @Setter @Getter
    public String email;
}
