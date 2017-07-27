package aisa.application.model;

import com.google.gson.annotations.SerializedName;

public class ContactType {

    @SerializedName("contacttypename")
    private String name;

    @SerializedName("contacttypeint")
    private int type;


    public ContactType(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
