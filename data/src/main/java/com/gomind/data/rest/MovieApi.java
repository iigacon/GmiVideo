package com.gomind.data.rest;

import com.gomind.data.entities.Credits;
import com.gomind.data.entities.Images;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MoviePopular;
import com.gomind.data.entities.MovieSimilars;
import com.gomind.data.entities.Videos;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieApi {
    @GET("/3/movie/popular")
    Observable<MoviePopular> moviePopulars(@Query("api_key") String api_key, @Query("page") long page);

    @GET("/3/movie/{id}")
    Observable<MovieDetail> movieDetail(@Path("id") String idMovie, @Query("api_key") String api_key);

    @GET("http://api.themoviedb.org/3/movie/{id}/similar")
    Observable<MovieSimilars> movieSimilars(@Path("id")String idMovie, @Query("api_key") String api_key, @Query("page")int page);

    @GET("/3/movie/{id}/credits")
    Observable<Credits> movieCredits(@Path("id")String idMovie, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/images")
    Observable<Images> movieImages(@Path("id")String idMovie, @Query("api_key")String api_key);

    @GET("/3/movie/{id}/videos")
    Observable<Videos> movieVideos(@Path("id")String idMovie, @Query("api_key")String api_key);
}
