package aisa.application.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by admin on 7/18/17.
 */

public class SubCategories implements Serializable{
    private String name;
    private String imagePath;

    public SubCategories(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

}
