package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.MovieList;

import java.util.List;

/**
 * Created by Duc on 9/10/16.
 */
public interface MovieCatelogyView extends View{
    void bindMovieCatelogy(List<MovieList> movieLists);
    void bindAccount(Account account);
}
