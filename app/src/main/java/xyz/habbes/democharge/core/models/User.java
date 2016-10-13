package xyz.habbes.democharge.core.models;

import retrofit2.Call;
import xyz.habbes.democharge.core.BaseModel;
import xyz.habbes.democharge.core.api.ApiConfig;

/**
 * User model
 * Created by Habbes on 13/10/2016.
 */
public class User extends BaseModel {
    public String name;
    public String email;

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
    public static Call<User> signUp(User user){
        return ApiConfig.getService().createUser(user);
    }
}
