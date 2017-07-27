package aisa.application.model;

public class Contacts{

    private int contactType;
    private String data;

    public Contacts(int contactType, String data){
        this.contactType = contactType;
        this.data = data;
    }

    public int getContactType() {
        return contactType;
    }

    public String getData() {
        return data;
    }
}
