package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;

/**
 * Created by linhl on 4/23/2018.
 */

public class DataDetailSpecialSaigonResult extends CommonApiResult {
    @Expose
    @Setter
    @Getter
    @SerializedName("data")
    private Data data;

    public static class Data {
        @Expose
        @Setter
        @Getter
        @SerializedName("danhsachgoiuudai")
        private List<Danhsachgoiuudai> danhsachgoiuudai;
        @Expose
        @Setter
        @Getter
        @SerializedName("danhsachcuahang")
        private List<Danhsachcuahang> danhsachcuahang;
        @Expose
        @Setter
        @Getter
        @SerializedName("chitietuudai")
        private List<Chitietuudai> chitietuudai;
    }

    public static class Danhsachgoiuudai {
        @Expose
        @Setter
        @Getter
        @SerializedName("TenGoiUuDai")
        private String TenGoiUuDai;
    }

    public static class Danhsachcuahang extends DataShopDetail{
        @Expose
        @Setter
        @Getter
        @SerializedName("TenDonVi")
        private String TenDonVi;
    }

    public static class Chitietuudai {
        @Expose
        @Setter
        @Getter
        @SerializedName("LinhVuc")
        private String LinhVuc;
        @Expose
        @Setter
        @Getter
        @SerializedName("ngaybatdau")
        private String ngaybatdau;
        @Expose
        @Setter
        @Getter
        @SerializedName("ngayketthuc")
        private String ngayketthuc;
        @Expose
        @Setter
        @Getter
        @SerializedName("MoTa")
        private String MoTa;
        @Expose
        @Setter
        @Getter
        @SerializedName("Slogan")
        private String Slogan;
        @Expose
        @Setter
        @Getter
        @SerializedName("TenDonVi")
        private String tenDonVi;
        @Expose
        @Setter
        @Getter
        @SerializedName("TenChuongTrinhUuDai")
        private String TenChuongTrinhUuDai;
        @Expose
        @Setter
        @Getter
        @SerializedName("DuongDanAnh")
        private String duongDanAnh;
    }
}
