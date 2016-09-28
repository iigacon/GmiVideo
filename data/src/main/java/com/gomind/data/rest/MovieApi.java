package com.gomind.data.rest;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.Credits;
import com.gomind.data.entities.ImagePersons;
import com.gomind.data.entities.Images;
import com.gomind.data.entities.Login;
import com.gomind.data.entities.MovieBase;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MovieLists;
import com.gomind.data.entities.MoviePopular;
import com.gomind.data.entities.MovieSimilars;
import com.gomind.data.entities.ObjectPostWatchList;
import com.gomind.data.entities.Person;
import com.gomind.data.entities.PersonMovieCredits;
import com.gomind.data.entities.PersonPopulars;
import com.gomind.data.entities.PostWatchList;
import com.gomind.data.entities.RequestToken;
import com.gomind.data.entities.Reviews;
import com.gomind.data.entities.Session;
import com.gomind.data.entities.Videos;
import com.gomind.data.entities.WatchListMovies;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("/3/movie/{id}/reviews")
    Observable<Reviews> movieReview(@Path("id")String idMovie,@Query("api_key")String api_key,@Query("page")int page);

    @GET("/3/movie/upcoming")
    Observable<MovieBase> movieUpComing(@Query("api_key")String api_key, @Query("page")int page);

    @GET("/3/movie/now_playing")
    Observable<MovieBase> movieNowPlaying(@Query("api_key")String api_key, @Query("page")int page);

    @GET("/3/movie/top_rated")
    Observable<MovieBase> movieTopRate(@Query("api_key")String api_key, @Query("page")int page);

    @GET("/3/discover/movie")
    Observable<MovieBase> movieDiscovery(@Query("api_key")String api_key, @Query("page")int page);

    @GET("/3/genre/{id}/movies")
    Observable<MovieBase> movieGenre(@Path("id")String id, @Query("api_key")String api_key, @Query("page")int page);

    @GET("/3/genre/movie/list")
    Observable<MovieLists> movieCatelogy(@Query("api_key")String api_key);

    @GET("/3/search/movie")
    Observable<MovieBase> movieSearch(@Query("api_key")String api_key, @Query("query")String query, @Query("page")int page);

    @GET("/3/person/popular")
    Observable<PersonPopulars> peoplePopular(@Query("api_key")String api_key, @Query("page")double page);

    @GET("/3/person/{id}")
    Observable<Person> getPerson(@Path("id")String id, @Query("api_key")String api_key);

    @GET("/3/person/{id}/movie_credits")
    Observable<PersonMovieCredits> getMovieCastPerson(@Path("id")String id, @Query("api_key")String api_key);

    @GET("/3/person/{id}/images")
    Observable<ImagePersons> getImagePerson(@Path("id")String id, @Query("api_key")String api_key);

    @GET("/3/search/person")
    Observable<PersonPopulars> searchPerson(@Query("api_key")String api_key, @Query("query") String query, @Query("page") int page);

    @GET("/3/authentication/token/new")
    Observable<RequestToken> requestToken(@Query("api_key")String api_key);

    @GET("/3/authentication/token/validate_with_login")
    Observable<Login> login(@Query("api_key")String api_key, @Query("request_token")String request_token, @Query("username")String username, @Query("password")String password);

    @GET("/3/authentication/session/new")
    Observable<Session> getSession(@Query("api_key")String api_key, @Query("request_token")String request_token);

    @GET("/3/account")
    Observable<Account> account(@Query("api_key")String api_key, @Query("session_id")String session_id);

    @GET("/3/account/{id}/watchlist/movies")
    Observable<WatchListMovies> getWatchListMovie(@Path("id")String id, @Query("api_key")String api_key, @Query("session_id")String session_id,@Query("page")int page);

    @POST("/3/account/{id}/watchlist")
    Observable<PostWatchList> postWatchList(@Path("id")String id, @Query("api_key")String api_key, @Query("session_id")String session_id, @Body ObjectPostWatchList postWatchList);
}
