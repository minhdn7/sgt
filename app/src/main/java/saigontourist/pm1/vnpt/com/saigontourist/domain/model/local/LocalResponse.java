package saigontourist.pm1.vnpt.com.saigontourist.domain.model.local;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 4/5/2018.
 */
public class LocalResponse {
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
    public List<Data> data = null;

    public static class Data {
        @SerializedName("id")
        @Expose
        @Setter @Getter
        public Integer id;

        @SerializedName("name")
        @Expose
        @Setter @Getter
        public String name;

        @Override
        public String toString() {
            return name;
        }
    }
}
