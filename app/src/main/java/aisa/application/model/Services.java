package aisa.application.model;

public class Services{
    private String name;
    private int price;
    private String description;

    public Services(String name, int price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
