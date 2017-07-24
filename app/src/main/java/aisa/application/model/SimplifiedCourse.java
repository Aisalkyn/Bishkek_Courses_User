package aisa.application.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Alier on 7/22/2017.
 */

public class SimplifiedCourse extends RealmObject {
    private String name;
    private String description;
    private RealmList<Image> images;

    public SimplifiedCourse(){}

    public RealmList<Image> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setImages(RealmList<Image> images) {
        this.images = images;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
