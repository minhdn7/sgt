package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;

/**
 * Created by linhl on 5/3/2018.
 */

public class InfoEnterpriseSaigonResult extends CommonApiResult {

    @Expose
    @Setter
    @Getter
    @SerializedName("Data")
    private Data Data;

    public static class Data {
        @Expose
        @Setter
        @Getter
        @SerializedName("MoTa")
        private String MoTa;
        @Expose
        @Setter
        @Getter
        @SerializedName("AnhDaiDien")
        private String AnhDaiDien;
    }
}
