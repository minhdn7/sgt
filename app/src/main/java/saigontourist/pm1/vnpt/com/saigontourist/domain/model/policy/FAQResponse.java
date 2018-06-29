package saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 23/4/2018.
 */
public class FAQResponse {
    @SerializedName("ErrorCode")
    @Expose
    @Setter @Getter
    public String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    @Setter @Getter
    public String errorDesc;

    @SerializedName("data")
    @Expose
    @Setter @Getter
    public Data data;

    public class Data {
        @SerializedName("Dsbinhluan")
        @Expose
        @Setter @Getter
        public List<Dsbinhluan> dsbinhluan = null;

    }

    public class Dsbinhluan  {
        @SerializedName("STT")
        @Expose
        @Setter @Getter
        public Integer sTT;

        @SerializedName("TieuDe")
        @Expose
        @Setter @Getter
        public String tieuDe;

        @SerializedName("NoiDung")
        @Expose
        @Setter @Getter
        public String noiDung;

    }
}
