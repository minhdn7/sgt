package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;

/**
 * Created by linhl on 5/7/2018.
 */

public class DataMemberCardResult extends CommonApiResult {
    @Expose
    @Setter
    @Getter
    @SerializedName("SoTheHoiVien")
    private String soTheHoiVien;
    @Expose
    @Setter
    @Getter
    @SerializedName("URLBarcode")
    private String urlBarcode;
    @Expose
    @Setter
    @Getter
    @SerializedName("URLQrcode")
    private String urlQrcode;
}
