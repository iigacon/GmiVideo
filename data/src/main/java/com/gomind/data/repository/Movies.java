package com.gomind.data.repository;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.Account_states;
import com.gomind.data.entities.Credits;
import com.gomind.data.entities.ImagePersons;
import com.gomind.data.entities.Images;
import com.gomind.data.entities.Keyword;
import com.gomind.data.entities.Login;
import com.gomind.data.entities.MovieBase;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MovieLists;
import com.gomind.data.entities.MoviePopular;
import com.gomind.data.entities.MovieSimilars;
import com.gomind.data.entities.Person;
import com.gomind.data.entities.PersonMovieCredits;
import com.gomind.data.entities.PersonPopulars;
import com.gomind.data.entities.PostWatchList;
import com.gomind.data.entities.Release_dates;
import com.gomind.data.entities.RequestToken;
import com.gomind.data.entities.Reviews;
import com.gomind.data.entities.Session;
import com.gomind.data.entities.Title;
import com.gomind.data.entities.Translation;
import com.gomind.data.entities.Videos;
import com.gomind.data.entities.WatchListMovies;

import java.util.List;

import rx.Observable;


/**
 * Created by Duc on 8/27/16.
 */
public interface Movies {
    Observable<MovieDetail> movieDetail(String id);
    Observable<Account_states> account_states(String id, String session);
    Observable<List<Title>> alternative_titles(String id);
    Observable<Credits> movieCredits(String id);
    Observable<Images> movieImages(String id);
    Observable<List<Keyword>> keywords(String id);
    Observable<List<Release_dates>> release_dates(String id);
    Observable<Videos> movieVideos(String id);
    Observable<List<Translation>> translations(String id);
    Observable<MovieSimilars> movieSimilars(String id, int page);
    Observable<Reviews> movieReviews(String id,int page);
    Observable<MoviePopular> moviePopulars(int page);
    Observable<MovieBase> movieUpComing(int page);
    Observable<MovieBase> movieNowPlaying(int page);
    Observable<MovieBase> movieTopRate(int page);
    Observable<MovieBase> movieDiscovery(int page);
    Observable<MovieBase> movieGenre(String id,int page);
    Observable<MovieLists> movieCatelogy();
    Observable<MovieBase> movieSearch(String query, int page);
    Observable<PersonPopulars> personPopular(double page);
    Observable<Person> getPerson(String id);
    Observable<PersonMovieCredits> getMovieCastPerson(String id);
    Observable<ImagePersons> getImagePerson(String id);
    Observable<PersonPopulars> searchPerson(String query, int page);
    Observable<RequestToken> requestToken();
    Observable<Login> login(String request_token, String username, String password);
    Observable<Account> account(String session_id);
    Observable<Session> getSession(String request_token);
    Observable<WatchListMovies> watchListMovie(String session_id, String id, int page);
    Observable<PostWatchList> postWatchList(String session_id, String id, String media_id, boolean watchlist);
}
