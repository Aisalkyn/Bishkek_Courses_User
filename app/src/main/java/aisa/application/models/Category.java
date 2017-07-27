package aisa.application.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alier on 7/22/2017.
 */

public class Category extends RealmObject{
    @PrimaryKey
    private String name;
    @SerializedName("subcategories")
    private RealmList<SubCategory> subCategories;

    public Category(){}

    public String getName() {
        return name;
    }

    public RealmList<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubCategories(RealmList<SubCategory> subCat) {
        this.subCategories = subCat;
    }
}
