package com.gomind.data.repository;

import com.gomind.data.entities.Configuration;
import com.gomind.data.rest.ConfigurationApi;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
/**
 * Created by Duc on 9/5/16.
 */
public class ConfigurationRepository implements Configurations{
    public static String BASE_URL = "http://api.themoviedb.org/";
    public static String API_KEY = "beff5456e1ab8e632d1851eaa3114fdd";
    private ConfigurationApi configurationApi;
    @Inject
    public ConfigurationRepository() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        configurationApi=retrofit.create(ConfigurationApi.class);
    }

    @Override
    public Observable<Configuration> getConfiguration() {
        return configurationApi.geConfiguration(API_KEY);
    }
}
