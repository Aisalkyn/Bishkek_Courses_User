package aisa.application.models;

/**
 * Created by admin on 7/11/17.
 */

public class Contacts {
    private int contacttype;
    private String data;

    public Contacts(int contacttype, String data) {
        this.contacttype = contacttype;
        this.data = data;
    }

    public int getContacttype() {
        return contacttype;
    }

    public String getData() {
        return data;
    }
}
