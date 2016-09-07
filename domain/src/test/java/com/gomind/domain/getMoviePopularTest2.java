package com.gomind.domain;

import com.gomind.data.entities.Movie;
import com.gomind.data.repository.MovieDataRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import java.util.List;

import rx.Scheduler;

import static junit.framework.Assert.assertNotNull;
/**
 * Created by Duc on 8/28/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk=23,manifest = Config.NONE)
public class getMoviePopularTest2 {
    private final static int FAKE_PAGE = 1;
    @Mock
    MovieDataRepository movieDataRepository;
    @Mock
    Scheduler mockuiScheduler;
    @Mock
    Scheduler mockExecutorScheduler;
    @Mock
    List<Movie> movies;
    @Mock
    GetMoviePopular getMoviePopular;
    @Before public void setUp()throws Exception{
        MockitoAnnotations.initMocks(this);
        movieDataRepository=new MovieDataRepository();
        getMoviePopular =new GetMoviePopular(FAKE_PAGE,movieDataRepository,mockuiScheduler,mockExecutorScheduler);
    }
    @Test public void testGetMoviePopularTest(){
        getMoviePopular.execute();
        assertNotNull(getMoviePopular.buildObserable());
    }

}
