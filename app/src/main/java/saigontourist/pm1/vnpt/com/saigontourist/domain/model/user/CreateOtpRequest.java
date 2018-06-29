package saigontourist.pm1.vnpt.com.saigontourist.domain.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 2/5/2018.
 */
public class CreateOtpRequest {
    @SerializedName("SoDienThoai")
    @Expose
    @Getter @Setter
    public String SoDienThoai;

    @SerializedName("Tokenhoivien")
    @Expose
    @Getter @Setter
    public String Tokenhoivien;


    public CreateOtpRequest(String soDienThoai, String tokenhoivien) {
        SoDienThoai = soDienThoai;
        Tokenhoivien = tokenhoivien;
    }
}
