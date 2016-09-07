package com.gomind.domain;

import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MoviePopular;
import com.gomind.data.repository.MovieDataRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static org.mockito.Mockito.only;

/**
 * Created by Duc on 8/28/16.
 */
public class getMoviePopularTest {
    private final static int FAKE_PAGE = 1;
    @Mock
    MovieDataRepository movieDataRepository;
    @Mock
    Scheduler mockuiScheduler;
    @Mock
    Scheduler mockExecutorScheduler;
    @Mock
    List<Movie> movies;
    @Before public void setUp()throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @Test public void testGetMoviePopularTest(){
        GetMoviePopular getMoviePopular =new GetMoviePopular(FAKE_PAGE,movieDataRepository,mockuiScheduler,mockExecutorScheduler);
        Mockito.when(movieDataRepository.moviePopulars(FAKE_PAGE)).thenReturn(getFakeObservableCharacterList());
        getMoviePopular.execute();
        Mockito.verify(movieDataRepository,only()).moviePopulars(FAKE_PAGE);
        Mockito.verifyNoMoreInteractions(movieDataRepository);
        Mockito.verifyZeroInteractions(mockuiScheduler);
        Mockito.verifyZeroInteractions(mockExecutorScheduler);
    }
    private Observable<MoviePopular> getFakeObservableCharacterList() {
        return Observable.just(new MoviePopular(FAKE_PAGE));
    }
}
