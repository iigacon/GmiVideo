package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Person;
import com.gomind.domain.GetPerson;
import com.gomind.gmivideo.vmp.view.PersonDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

public class PersonPresenter implements Presenter {
    private GetPerson getPerson;
    private Subscription mMovieGenre;
    private PersonDetailView personDetailView;


    @Inject
    public PersonPresenter(GetPerson getPerson) {
        this.getPerson = getPerson;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mMovieGenre.unsubscribe();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        personDetailView = (PersonDetailView) v;
    }

    @Override
    public void onCreate() {
        mMovieGenre=getPerson.execute().subscribe(this::OnMovieGenreReceived,this::OnError);
    }
    @Override
    public void loadMore(){

    }
    public void OnMovieGenreReceived(Person person){
        personDetailView.binPerson(person);
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }

}
