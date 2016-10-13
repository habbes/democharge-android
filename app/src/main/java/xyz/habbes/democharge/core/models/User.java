package xyz.habbes.democharge.core.models;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.habbes.democharge.core.BaseModel;
import xyz.habbes.democharge.core.api.ApiConfig;
import xyz.habbes.democharge.core.helpers.ValueCallback;

/**
 * User model
 * Created by Habbes on 13/10/2016.
 */
public class User extends BaseModel {
    public String name;
    public String email;
    public String password;
    public Wallet wallet;
    public AccessToken accessToken;

    /**
     * creates new user with specified details
     * @param name
     * @param email
     * @param password
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * creates user with given name and email
     * @param name
     * @param email
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public User(String name, String email){
        this(name, email, null);
    }

    /**
     * creates new user with null fields
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public User(){
        this(null, null);
    }

    @Override
    public String toString(){
        return name;
    }

    /**
     * login the user with specified email and password
     * @param email
     * @param password
     * @return
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static Call<AccessToken> login(String email, String password){
        LoginCredentials credentials = new LoginCredentials(email, password);
        return ApiConfig.getService().login(credentials);
    }

    /**
     * create an account for the specified user
     * @param user
     * @return
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static Call<User> register(User user){
        return ApiConfig.getService().createUser(user);
    }

    /**
     * fetch the user with the given id
     * @param id
     * @param accessToken the access token of the user
     * @return
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public static Call<User> getById(String id, String accessToken){
        return ApiConfig.getService().getUserById(id, accessToken);
    }

    /**
     * get user's wallet
     * @param callback callback with methods to call when wallet has been found
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public void getWallet(final ValueCallback<Wallet> callback){
        if(wallet != null){
            callback.onValue(wallet);
            return;
        }

        ApiConfig.getService().getUserWallet(id, accessToken.id).enqueue(new Callback<Wallet>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                Wallet fetchedWallet = response.body();
                if(fetchedWallet == null){
                    callback.onError(null);
                }

                wallet = fetchedWallet;
                wallet.user = User.this;
                callback.onValue(wallet);
            }

            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
