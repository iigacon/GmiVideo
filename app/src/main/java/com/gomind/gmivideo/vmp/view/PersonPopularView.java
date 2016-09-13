package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.PersonPopular;

import java.util.List;

/**
 * Created by Duc on 9/13/16.
 */
public interface PersonPopularView extends View{
    void binPerson(List<PersonPopular> personPopulars);
    void startDetailPerson(String id);
    void countLoadMore(int count);
    void error(Throwable e);
}
