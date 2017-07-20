package aisa.application.models;

import java.util.List;

/**
 * Created by admin on 7/19/17.
 */

public class SimplifiedCourse {

    private String name;
    private String description;
    private List<Images> images;

    public SimplifiedCourse(String name, String description, List<Images> images) {
        this.name = name;
        this.description = description;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Images> getImages() {
        return images;
    }
}
