package xyz.habbes.democharge.core.models;

import xyz.habbes.democharge.core.BaseModel;

/**
 * represents the API wallet model
 * Created by Habbes on 13/10/2016.
 */
public class Wallet extends BaseModel {

    public double balance;
    public String userId;
    public User user;

    @Override
    public String toString(){
        return "Wallet balance: " + balance;
    }

}
