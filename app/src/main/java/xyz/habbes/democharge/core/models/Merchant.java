package xyz.habbes.democharge.core.models;

/**
 * represents the merchant model
 * Created by Habbes on 12/10/2016.
 */
public class Merchant {
    public String name;
    public String location;
    public String locationName;
    public String phone;
    public String website;
    public double rating;

    @Override
    public String toString(){
        return name;
    }
}
