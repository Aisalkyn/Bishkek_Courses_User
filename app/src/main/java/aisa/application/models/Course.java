package aisa.application.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 7/7/17.
 */

public class Course {
    private String name;
    private String description;
    private List<Branches> branches;
    private List<Contacts> contacts;
    private List<Model_Services>  services;
    private List<Categories>  categories;
    private List<Images> images;


    public Course(String name, String description, List<Branches> branches, List<Contacts> contacts, List<Model_Services> services, List<Categories> categories, List<Images> images) {
        this.name = name;
        this.description = description;
        this.branches = branches;
        this.contacts = contacts;
        this.services = services;
        this.categories = categories;
        this.images = images;
    }

    public List<Images> getImages() {
        return images;
    }


    public List<Branches> getBranches() {
        return branches;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public List<Contacts>  getContacts() {
        return contacts;
    }

    public List<Model_Services>  getServices() {
        return services;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
