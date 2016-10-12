package xyz.habbes.democharge.core.models;

import java.util.List;

import retrofit2.Call;
import xyz.habbes.democharge.core.BaseModel;
import xyz.habbes.democharge.core.api.ApiConfig;

/**
 * Created by Habbes on 12/10/2016.
 * represents  a Deal model
 */
public class Deal extends BaseModel {
    public String name;
    public String description;
    public double originalPrice;
    public double discountPrice;
    public String image;
    public String product;
    public String merchantId;
    public Merchant merchant;

    @Override
    public String toString(){
        return name;
    }

    /**
     * fetch all deals from the API
     * @return list of deals from the API
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    public static Call<List<Deal>> fetch(){
        return ApiConfig.getService().getDeals();
    }

    /**
     * fetch a deal by its id
     * @param id
     * @return
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    public static Call<Deal> fetchById(String id){
        return ApiConfig.getService().getDealById(id);
    }
}
