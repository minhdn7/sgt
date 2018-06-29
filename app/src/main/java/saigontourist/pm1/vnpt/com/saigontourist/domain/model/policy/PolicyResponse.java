package saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PolicyResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Getter @Setter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Getter @Setter
    public String errorDesc;

    @SerializedName("Data")
    @Expose
    @Getter @Setter
    public List<Datum> data = null;

    public class Datum {
        @SerializedName("TieuDe")
        @Expose
        @Getter @Setter
        public String tieuDe;

        @SerializedName("NoiDung")
        @Expose
        @Getter @Setter
        public String noiDung;
    }

}
