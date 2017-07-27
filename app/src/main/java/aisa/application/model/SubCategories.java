package aisa.application.model;

import java.io.Serializable;

public class SubCategories implements Serializable{
    private String name;
    private String imgpath;

    public SubCategories(String name, String imgpath){
        this.name = name;
        this.imgpath = imgpath;
    }

    public String getName() {
        return name;
    }

    public String getImgpath() {
        return imgpath;
    }
}
