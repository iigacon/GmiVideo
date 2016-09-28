package com.gomind.gmivideo.data.repository;

import com.gomind.data.entities.MoviePopular;
import com.gomind.data.repository.MovieDataRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 23,manifest=Config.NONE)
public class GetMovieDataRepositoryTest {
    long FAKE_PAGE=1;
    MovieDataRepository movieDataRepository;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Before
    public void setup(){
        movieDataRepository= new MovieDataRepository();
    }
    @Test
    public void testGetMoviePopular() {
//        when(movieDataRepository.moviePopulars(FAKE_PAGE)).thenReturn(getFakeMoviePopular());
//        Mockito.verify(movieApi).moviePopulars("beff5456e1ab8e632d1851eaa3114fdd",FAKE_PAGE);
//        movieDataRepository.moviePopulars(FAKE_PAGE);
         Observable<MoviePopular> moviePopularObservable=movieDataRepository.moviePopulars(FAKE_PAGE);
         assertNotNull(moviePopularObservable);
    }
//    private Observable<MoviePopular> getFakeMoviePopular(){
//        return Observable.just(new MoviePopular(FAKE_PAGE));
//    }
}
