package aisa.application.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alier on 7/22/2017.
 */

public class Image extends RealmObject {

    @PrimaryKey
    @SerializedName("imgpath")
    private String imagePath;
    private boolean isLogo;

    public Image(){}

    public Image(boolean isLogo, String imagePath){
        this.isLogo = isLogo;
        this.imagePath = imagePath;
    }

    public boolean isLogo() {
        return isLogo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setLogo(boolean logo) {
        isLogo = logo;
    }
}
