package aisa.application.models;

/**
 * Created by admin on 7/17/17.
 */


public class Model_Services {
    String name;
    String description;
    String price;



    public Model_Services (String n, String d, String p)
    {
        this.name = n;
        this.description = d;
        this.price = p;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}