package com.gomind.data.repository;


import com.gomind.data.entities.Account_states;
import com.gomind.data.entities.Credits;
import com.gomind.data.entities.Images;
import com.gomind.data.entities.Keyword;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MoviePopular;
import com.gomind.data.entities.MovieSimilars;
import com.gomind.data.entities.Release_dates;
import com.gomind.data.entities.Reviews;
import com.gomind.data.entities.Title;
import com.gomind.data.entities.Translation;
import com.gomind.data.entities.Videos;
import com.gomind.data.rest.MovieApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


/**
 * Created by Duc on 8/28/16.
 */

public class MovieDataRepository implements Movies {
    public static String BASE_URL = "http://api.themoviedb.org/";
    public static String API_KEY = "beff5456e1ab8e632d1851eaa3114fdd";
    private final MovieApi movieApi;

    @Inject
    public MovieDataRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        movieApi = retrofit.create(MovieApi.class);
    }


    @Override
    public Observable<MovieDetail> movieDetail(String id) {
        return movieApi.movieDetail(id, API_KEY);
    }

    @Override
    public Observable<Account_states> account_states(String id, String session) {
        return null;
    }

    @Override
    public Observable<List<Title>> alternative_titles(String id) {
        return null;
    }

    @Override
    public Observable<Credits> movieCredits(String id) {
        return movieApi.movieCredits(id,API_KEY);
    }

    @Override
    public Observable<Images> movieImages(String id) {
        return movieApi.movieImages(id,API_KEY);
    }

    @Override
    public Observable<List<Keyword>> keywords(String id) {
        return null;
    }

    @Override
    public Observable<List<Release_dates>> release_dates(String id) {
        return null;
    }

    @Override
    public Observable<Videos> movieVideos(String id) {
        return movieApi.movieVideos(id, API_KEY);
    }

    @Override
    public Observable<List<Translation>> translations(String id) {
        return null;
    }

    @Override
    public Observable<MovieSimilars> movieSimilars(String id, int page) {
        return movieApi.movieSimilars(id, API_KEY, page);
    }

    @Override
    public Observable<Reviews> reviews(String id, long page) {
        return null;
    }

    @Override
    public Observable<MoviePopular> moviePopulars(int page) {
        return movieApi.moviePopulars(API_KEY, page);
    }
}
