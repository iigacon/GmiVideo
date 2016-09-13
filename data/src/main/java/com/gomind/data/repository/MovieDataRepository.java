package com.gomind.data.repository;

import com.gomind.data.entities.Account_states;
import com.gomind.data.entities.Credits;
import com.gomind.data.entities.ImagePersons;
import com.gomind.data.entities.Images;
import com.gomind.data.entities.Keyword;
import com.gomind.data.entities.MovieBase;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MovieLists;
import com.gomind.data.entities.MoviePopular;
import com.gomind.data.entities.MovieSimilars;
import com.gomind.data.entities.Person;
import com.gomind.data.entities.PersonMovieCredits;
import com.gomind.data.entities.PersonPopulars;
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
        return movieApi.movieCredits(id, API_KEY);
    }

    @Override
    public Observable<Images> movieImages(String id) {
        return movieApi.movieImages(id, API_KEY);
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
    public Observable<Reviews> movieReviews(String id, int page) {
        return movieApi.movieReview(id, API_KEY, page);
    }

    @Override
    public Observable<MoviePopular> moviePopulars(int page) {
        return movieApi.moviePopulars(API_KEY, page);
    }

    @Override
    public Observable<MovieBase> movieUpComing(int page) {
        return movieApi.movieUpComing(API_KEY, page);
    }

    @Override
    public Observable<MovieBase> movieNowPlaying(int page) {
        return movieApi.movieNowPlaying(API_KEY, page);
    }

    @Override
    public Observable<MovieBase> movieTopRate(int page) {
        return movieApi.movieTopRate(API_KEY, page);
    }

    @Override
    public Observable<MovieBase> movieDiscovery(int page) {
        return movieApi.movieDiscovery(API_KEY, page);
    }

    @Override
    public Observable<MovieBase> movieGenre(String id, int page) {
        return movieApi.movieGenre(id, API_KEY, page);
    }

    @Override
    public Observable<MovieLists> movieCatelogy() {
        return movieApi.movieCatelogy(API_KEY);
    }

    @Override
    public Observable<MovieBase> movieSearch(String query, int page) {
        return movieApi.movieSearch(API_KEY, query, page);
    }

    @Override
    public Observable<PersonPopulars> personPopular(double page) {
        return movieApi.peoplePopular(API_KEY, page);
    }

    @Override
    public Observable<Person> getPerson(String id) {
        return movieApi.getPerson(id, API_KEY);
    }

    @Override
    public Observable<PersonMovieCredits> getMovieCastPerson(String id) {
        return movieApi.getMovieCastPerson(id, API_KEY);
    }

    @Override
    public Observable<ImagePersons> getImagePerson(String id) {
        return movieApi.getImagePerson(id, API_KEY);
    }


}
