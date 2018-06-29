package saigontourist.pm1.vnpt.com.saigontourist.app.messagebus;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/19/2018.
 */

public class MessageEvent {

    public static class MessageSpecialVpointFramgent {

        //Vpoint
        @Setter
        @Getter
        private int fieldVpoint = 0;
        @Setter
        @Getter
        private int enterPrise = 0;
        @Setter
        @Getter
        private int city = 0;
        @Setter
        @Getter
        private String textSearch = "";
    }

    public static class MessageSpecialSaigonFramgent {
        //Saigon
        @Setter
        @Getter
        private int provinceCode = 0;
        @Setter
        @Getter
        private int tradeMark = 0;
        @Setter
        @Getter
        private int fieldTouris = 0;
        @Setter
        @Getter
        private String textSearch = "";
    }

    public static class MessageCategorySpecial {
        //Saigon
        @Setter
        @Getter
        private int id;
        @Setter
        @Getter
        private String nameValue = "";

        @Setter
        @Getter
        private int typeCategory;

        public MessageCategorySpecial(int typeCategory) {
            this.typeCategory = typeCategory;
        }

        public MessageCategorySpecial(int id, String nameValue) {
            this.id = id;
            this.nameValue = nameValue;
        }

        public MessageCategorySpecial(int id, String nameValue, int typeCategory) {
            this.id = id;
            this.nameValue = nameValue;
            this.typeCategory = typeCategory;
        }
    }

    public static class MessageTypeCategoryAtivity {

        @Setter
        @Getter
        private int typeCategory;

        public MessageTypeCategoryAtivity(int typeCategory) {
            this.typeCategory = typeCategory;
        }

    }

    public static class MessageIdDetailAtivity {

        @Setter
        @Getter
        private int idDetail;

        @Setter
        @Getter
        private int typeDetail;

        @Getter
        @Setter
        private int idEnterprise;

        public MessageIdDetailAtivity(int idDetail) {
            this.idDetail = idDetail;
        }

        public MessageIdDetailAtivity(int idDetail, int typeDetail) {
            this.idDetail = idDetail;
            this.typeDetail = typeDetail;
        }

        public MessageIdDetailAtivity(int idDetail, int typeDetail, int idEnterprise) {
            this.idDetail = idDetail;
            this.typeDetail = typeDetail;
            this.idEnterprise = idEnterprise;
        }
    }

    public static class ShopOnMapFilter {
        @Setter
        @Getter
        private String searchingText;
        @Setter
        @Getter
        private int typeSearch;
        @Setter
        @Getter
        private int kindOfSearch;

        @Setter
        @Getter
        private int field;

        @Setter
        @Getter
        private String strField;

        @Setter
        @Getter
        private int radius;

        @Setter
        @Getter
        private String strRadius;

        @Setter
        @Getter
        private int enterpriseId;

        @Setter
        @Getter
        private String strEnterpriseId;
        @Setter
        @Getter
        private int chuongTrinhUuDaiId;


    }

    public static class StringSearchFilter {
        @Setter
        @Getter
        private String searchingText;
        @Setter
        @Getter
        private int typeSearch;

        public StringSearchFilter(String searchingText) {
            this.searchingText = searchingText;
        }

        public StringSearchFilter(String searchingText, int typeSearch) {
            this.searchingText = searchingText;
            this.typeSearch = typeSearch;
        }
    }

    public static class ReplaceFragmentMessage {
    }

}

