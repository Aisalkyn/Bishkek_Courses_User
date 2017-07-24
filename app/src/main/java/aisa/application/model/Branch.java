package aisa.application.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alier on 7/22/2017.
 */

public class Branch extends RealmObject{

    @PrimaryKey
    private String phone;
    private String latitude;
    private String longitude;
    private String address;

    public Branch(){}

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return Double.valueOf(latitude);
    }

    public double getLongitude() {
        return Double.valueOf(longitude);
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
