package xyz.habbes.democharge.core.models;

import xyz.habbes.democharge.core.BaseModel;

/**
 * represents an access token used for
 * authentication on the API.
 * The actual token is in the id property
 * Created by Habbes on 13/10/2016.
 */
public class AccessToken extends BaseModel {
    public String userId;
    public User user;
    public long ttl;

    @Override
    public String toString(){
        return this.id;
    }
}
