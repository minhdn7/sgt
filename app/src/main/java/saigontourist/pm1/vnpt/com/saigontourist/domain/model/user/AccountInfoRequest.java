package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class AccountInfoRequest {
    @SerializedName("token")
    @Getter @Setter
    @Expose
    private String token;

    public AccountInfoRequest(String token) {
        this.token = token;
    }
}
