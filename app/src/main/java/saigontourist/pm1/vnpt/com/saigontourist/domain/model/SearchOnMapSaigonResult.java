package saigontourist.pm1.vnpt.com.saigontourist.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;

/**
 * Created by linhl on 5/2/2018.
 */

public class SearchOnMapSaigonResult extends CommonApiResult {
    @Expose
    @Setter
    @Getter
    @SerializedName("data")
    private List<Data> data;

    public static class Data {
        @Expose
        @Setter
        @Getter
        @SerializedName("DiaChi")
        private String DiaChi;
        @Expose
        @Setter
        @Getter
        @SerializedName("GPS")
        private String GPS;
        @Expose
        @Setter
        @Getter
        @SerializedName("TenDonVi")
        private String TenDonVi; @Expose
        @Setter
        @Getter
        @SerializedName("AnhDaiDien")
        private String anhDaiDien;
        @Expose
        @Setter
        @Getter
        @SerializedName("DienThoai")
        private String dienThoai;
    }
}
