package aisa.application.models;

/**
 * Created by admin on 7/7/17.
 */

public class Branches {
    private String phone;
    private String latitude;
    private String longitude;
    private  String address;

    public Branches(String phone, String latitude, String longitude, String address) {
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }



}
