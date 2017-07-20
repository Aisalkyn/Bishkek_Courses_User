package aisa.application.models;

/**
 * Created by admin on 7/7/17.
 */

public class Branches {
    private String phone;
    private double latitude;
    private double longitude;
    private  String address;


    private String _id;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String get_id() {
        return _id;
    }


}
