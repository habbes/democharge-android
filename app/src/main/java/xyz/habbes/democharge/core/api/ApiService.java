package xyz.habbes.democharge.core.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.habbes.democharge.core.models.AccessToken;
import xyz.habbes.democharge.core.models.Deal;
import xyz.habbes.democharge.core.models.LoginCredentials;
import xyz.habbes.democharge.core.models.User;
import xyz.habbes.democharge.core.models.Wallet;

/**
 * Created by Habbes on 12/10/2016.
 */
public interface ApiService {

    // DEALS endpoints

    /**
     * find all deals and their merchants
     * @return
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    @GET("deals?include=merchant")
    Call<List<Deal>> getDeals();

    /**
     * find deal by id and its merchat
     * @param id
     * @return
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    @GET("deals/{id}?include=merchant")
    Call<Deal> getDealById(@Path("id") String id);

    // USERS endpoints

    /**
     * login user, returning access token to user for
     * subsequent requests
     * @param credentials
     * @return
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    @POST("users/login?include=user")
    Call<AccessToken> login(@Body LoginCredentials credentials);

    /**
     * create new user
     * @param user
     * @return
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    @POST("users")
    Call<User> createUser(@Body User user);

    /**
     * find user by id
     * @param id
     * @param accessToken
     * @return
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    @GET("users/{id}")
    Call<User> getUserById(@Path("id") String id, @Query("access_token") String accessToken );

    /**
     * find user's wallet
     * @param userId
     * @param accessToken
     * @return
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    @GET("users/{id}/wallet")
    Call<Wallet> getUserWallet(@Path("id") String userId, @Query("access_token") String accessToken);
}
