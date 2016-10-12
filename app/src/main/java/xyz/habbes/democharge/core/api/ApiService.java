package xyz.habbes.democharge.core.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.habbes.democharge.core.models.Deal;

/**
 * Created by Habbes on 12/10/2016.
 */
public interface ApiService {
    @GET("/deals")
    Call<List<Deal>> getDeals();
}
