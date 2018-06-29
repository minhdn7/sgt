package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangePhoneNumberRequest {
    @SerializedName("MaMayUuid")
    @Expose
    @Getter @Setter
    public String maMayUuid;

    @SerializedName("Tokenhoivien")
    @Expose
    @Getter @Setter
    public String tokenhoivien;

    @SerializedName("SoDienThoai")
    @Expose
    @Getter @Setter
    public String soDienThoai;
}
