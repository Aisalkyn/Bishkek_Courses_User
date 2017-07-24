package aisa.application.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SubCategory extends RealmObject{
    @PrimaryKey
    private String name;
    @SerializedName("imgpath")
    private String imagePath;

    public SubCategory(){}

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }
}