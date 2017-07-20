package aisa.application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aisa.application.Contact;
import aisa.application.Services;

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
        return images != null ? images : new ArrayList<Images>();
    }


    public List<Branches> getBranches() {
        return branches != null ? branches : new ArrayList<Branches>();
    }

    public List<Categories> getCategories() {
        return categories != null ? categories : new ArrayList<Categories>();
    }

    public List<Contacts>  getContacts() {
        return contacts != null ? contacts : new ArrayList<Contacts>();
    }

    public List<Model_Services>  getServices() {
        return services != null ? services : new ArrayList<Model_Services>();
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
