package xyz.habbes.democharge.core.models;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.habbes.democharge.core.BaseModel;
import xyz.habbes.democharge.core.helpers.ValueCallback;

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

    /**
     * gets the user associated to this token
     * @param callback callback to call when user is found
     */
    public void getUser(final ValueCallback<User> callback){
        if(user != null){
            // if user already found, return immediately
            callback.onValue(user);
            return;
        }
        User.getById(id, userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User fetchedUser = response.body();
                if(fetchedUser == null){
                    callback.onError(null);
                    return;
                }

                // save fetched instance
                user = fetchedUser;
                user.accessToken = AccessToken.this;
                callback.onValue(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
