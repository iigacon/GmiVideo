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
    Observable<Reviews> reviews(String id,long page);
    Observable<MoviePopular> moviePopulars(int page);
}
