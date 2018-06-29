package saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 4/20/2018.
 */

public class DataCategoryResult {
    @SerializedName("id")
    @Setter
    @Getter
    private Integer id;

    @SerializedName("name")
    @Setter
    @Getter
    private String name;

    public DataCategoryResult(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
