package xyz.habbes.democharge.core.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Habbes on 12/10/2016.
 */
public class ApiConfig {
    private static ApiService service;
    public static final String BASE_URL = "http://b35a5f1b.ngrok.io/api/";

    public static ApiService getService() {
        if(service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ApiService.class);
        }
        return service;
    }

    private ApiConfig() {
    }
}
