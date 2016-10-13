package xyz.habbes.democharge.core.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.habbes.democharge.core.models.AccessToken;
import xyz.habbes.democharge.core.models.Deal;
import xyz.habbes.democharge.core.models.LoginCredentials;
import xyz.habbes.democharge.core.models.User;

/**
 * Created by Habbes on 12/10/2016.
 */
public interface ApiService {

    // DEALS endpoints

    @GET("deals?include=merchant")
    Call<List<Deal>> getDeals();

    @GET("deals/{id}?include=merchant")
    Call<Deal> getDealById(@Path("id") String id);

    // USERS endpoints

    @POST("users/login?include=user")
    Call<AccessToken> login(@Body LoginCredentials credentials);

    @POST("users")
    Call<User> createUser(@Body User user);
}
