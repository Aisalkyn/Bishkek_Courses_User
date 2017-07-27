package aisa.application.model;

public class Branches {
    private String phone;
    private String latitude;
    private String longitude;
    private String address;

    public Branches(String address, String phone, String latitude, String longitude) {
        this.address = address;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
