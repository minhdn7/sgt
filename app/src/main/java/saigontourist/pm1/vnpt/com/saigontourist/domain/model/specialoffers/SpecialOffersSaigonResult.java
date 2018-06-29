package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;

/**
 * Created by THAOPX on 4/27/2017.
 */

public class SpecialOffersSaigonResult extends CommonApiResult implements Serializable {
    @SerializedName("data")
    @Setter
    @Getter
    private DataOffers dataOffers;
    public class DataOffers {
        @Expose
        @SerializedName("Dsuudai")
        @Setter
        @Getter
        List<SpecialOffer> listSpecial;
    }
   public class SpecialOffer extends SpecialOffersObject {
        @Expose
        @SerializedName("TenChuongTrinhUuDai")
        @Setter
        @Getter
        public String tenChuongTrinhUuDai;
        @Expose
        @SerializedName("TiLeTichDiem")
        @Setter
        @Getter
        public String tiLeTichDiem;
        @Expose
        @SerializedName("TenDonVi")
        @Setter
        @Getter
        public String tenDonVi;
        @Expose
        @SerializedName("LinhVuc")
        @Setter
        @Getter
        public String linhVuc;
        @Expose
        @SerializedName("DuongDanAnh")
        @Setter
        @Getter
        public String duongDanAnh;
        @Expose
        @SerializedName("Id")
        @Setter
        @Getter
        public int chuongTrinhUuDaiId;@Expose
        @SerializedName("TyLeUuDai")
        @Setter
        @Getter
        public double tyLeUuDai;
       @Expose
       @SerializedName("TyLeGiam")
       @Setter
       @Getter
       public String tyLeGiam;

        public SpecialOffer(int typeView) {
            super(typeView);
        }
    }
}
