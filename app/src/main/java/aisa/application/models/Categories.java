package aisa.application.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 7/18/17.
 */


public class Categories implements Serializable {
    private String name;
    private List<SubCategories> subcategories;

    public Categories(String name, List<SubCategories> subCategories){
        this.name = name;
        this.subcategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public List<SubCategories> getSubCat() {
        return subcategories;
    }
}