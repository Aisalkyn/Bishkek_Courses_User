package aisa.application.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alier on 7/22/2017.
 */

public class Contact extends RealmObject{
    @PrimaryKey
    @SerializedName("contacttype")
    private int contactType;
    private String data;

    public Contact(){
    }

    public int getContactType() {
        return contactType;
    }

    public String getData() {
        return data;
    }

    public void setContactType(int contactType) {
        this.contactType = contactType;
    }

    public void setData(String data) {
        this.data = data;
    }
}
