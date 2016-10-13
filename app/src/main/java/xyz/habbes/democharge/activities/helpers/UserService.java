package xyz.habbes.democharge.activities.helpers;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.habbes.democharge.core.helpers.ValueCallback;
import xyz.habbes.democharge.core.models.AccessToken;
import xyz.habbes.democharge.core.models.User;

/**
 * used to provide access to currently logged in user
 * across activities
 * Created by Habbes on 13/10/2016.
 */
public class UserService {

    /**
     * currently logged in user
     */
    private static User currentUser;

    /**
     * get the currently logged in user
     * @param context
     * @param callback
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static void getCurrentUser(Context context, ValueCallback<User> callback){

        if(currentUser != null){
            callback.onValue(currentUser);
        }

        fetchUser(context, callback);
    }

    /**
     * fetch user from the API, used when currentUser instance is not set
     * @param context
     * @param callback
     */
    private static void fetchUser(Context context, final ValueCallback<User> callback){
        AccessToken token = LoginService.getCurrentToken(context);
        if(token == null){
            callback.onError(null);
            return;
        }

        User.getById(token.userId, token.id).enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user != null){
                    callback.onValue(user);
                    return;
                }
                currentUser = user;
                callback.onValue(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(null);
            }
        });


    }
}
