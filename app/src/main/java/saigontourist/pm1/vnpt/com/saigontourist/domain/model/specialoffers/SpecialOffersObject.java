package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/18/2018.
 */

public class SpecialOffersObject {
    @Expose
    @Setter
    @Getter
    private int typeView;

    public SpecialOffersObject(int typeView) {
        this.typeView = typeView;
    }
}
