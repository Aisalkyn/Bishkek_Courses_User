package aisa.application.models;

/**
 * Created by admin on 7/17/17.
 */

public class Model_Contact {
    String contactType;
    String contactLink;


    public Model_Contact (String t, String l)
    {
        this.contactType = t;
        this.contactLink = l;


    }

    public String getContactType() {
        return contactType;
    }

    public String getContactLink() {
        return contactLink;
    }




}