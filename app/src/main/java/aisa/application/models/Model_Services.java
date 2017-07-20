package aisa.application.models;

/**
 * Created by admin on 7/17/17.
 */


public class Model_Services {
    String serviceName;
    String serviceDescr;
    String servicePrice;



    public Model_Services (String n, String d, String p)
    {
        this.serviceName = n;
        this.serviceDescr = d;
        this.servicePrice = p;

    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescr() {
        return serviceDescr;
    }

    public String getServicePrice() {
        return servicePrice;
    }
}