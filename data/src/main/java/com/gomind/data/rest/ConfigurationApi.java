package com.gomind.data.rest;

import com.gomind.data.entities.Configuration;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Duc on 9/5/16.
 */
public interface ConfigurationApi {
    @GET("/3/configuration")
    Observable<Configuration> geConfiguration(@Query("api_key")String api_key);
}
