package aisa.application.model;


import java.io.Serializable;
import java.util.List;

public class Categories implements Serializable{
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
