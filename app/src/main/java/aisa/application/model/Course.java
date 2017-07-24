package aisa.application.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alier on 7/22/2017.
 */

public class Course extends RealmObject {
    @PrimaryKey
    private String name;
    private String description;
    private RealmList<Image> images;
    private RealmList<Service> services;
    private RealmList<Contact> contacts;
    private RealmList<Category> categories;
    private RealmList<Branch> branches;

    public Course(){}

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public RealmList<Branch> getBranches() {
        return branches;
    }

    public RealmList<Category> getCategories() {
        return categories;
    }

    public RealmList<Contact> getContacts() {
        return contacts;
    }

    public RealmList<Image> getImages() {
        return images;
    }

    public RealmList<Service> getServices() {
        return services;
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

    public void setBranches(RealmList<Branch> branches) {
        this.branches = branches;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }

    public void setContacts(RealmList<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setServices(RealmList<Service> services) {
        this.services = services;
    }
}
