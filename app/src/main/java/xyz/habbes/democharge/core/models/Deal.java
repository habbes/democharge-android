package xyz.habbes.democharge.core.models;

import java.util.List;

import retrofit2.Call;
import xyz.habbes.democharge.core.api.ApiConfig;

/**
 * Created by Habbes on 12/10/2016.
 * represents  a Deal model
 */
public class Deal {
    public String name;
    public String description;
    public double originalPrice;
    public double discountPrice;
    public String image;
    public String product;

    @Override
    public String toString(){
        return name;
    }

    /**
     * fetch all deals from the API
     * @return list of deals from the API
     */
    public static Call<List<Deal>> fetch(){
        return ApiConfig.getService().getDeals();
    }
}
