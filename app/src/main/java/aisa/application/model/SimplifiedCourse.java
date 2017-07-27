package aisa.application.model;

import java.util.List;

public class SimplifiedCourse{

    private String name;
    private String description;
    private List<Images> images;

    public SimplifiedCourse(String n, String d, List<Images> listImg) {
        this.name = n;
        this.description = d;
        this.images = listImg;
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
