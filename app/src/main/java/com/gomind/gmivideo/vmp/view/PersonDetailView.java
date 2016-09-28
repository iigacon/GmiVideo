package com.gomind.gmivideo.vmp.view;

import com.gomind.data.entities.ImagePerson;
import com.gomind.data.entities.Person;
import com.gomind.data.entities.PersonMovieCredit;

import java.util.List;


public interface PersonDetailView extends View{
    void binPerson(Person person);
    void binMovieCastPerson(List<PersonMovieCredit> movieCredit);
    void binImageProfile(List<ImagePerson> imagePersons);
}
