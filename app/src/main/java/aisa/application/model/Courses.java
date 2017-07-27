package aisa.application.model;

import java.util.List;

public class Courses{

    private String name;
    private String description;
    private List<Branches> branches;
    private List<Contacts> contacts;
    private List<Services> services;
    private List<Categories> categories;
    private List<Images> images;

    public Courses(String n, String d, List<Branches> listB, List<Contacts> listC,
                   List<Services> listS, List<Categories> listCat, List<Images> listImg) {
        this.name = n;
        this.description = d;
        this.branches = listB;
        this.contacts = listC;
        this.services = listS;
        this.categories = listCat;
        this.images = listImg;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Branches> getBranches() {
        return branches;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public List<Images> getImages() {
        return images;
    }

    public List<Services> getServices() {
        return services;
    }
}
