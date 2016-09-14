package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.PersonPopular;

import java.util.List;


public interface SearchPersonView extends View{
    void binPerson(List<PersonPopular> persons);
    void countLoadMore(int count);
    void error(Throwable error);
}
