package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.PersonMovieCredits;
import com.gomind.domain.GetMovieCastPerson;
import com.gomind.gmivideo.vmp.view.PersonDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

public class MovieCastPersonPresenter implements Presenter {
    private GetMovieCastPerson getMovieCastPerson;
    private Subscription mMovieGenre;
    private PersonDetailView personDetailView;


    @Inject
    public MovieCastPersonPresenter(GetMovieCastPerson getMovieCastPerson) {
        this.getMovieCastPerson = getMovieCastPerson;
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
        mMovieGenre=getMovieCastPerson.execute().subscribe(this::OnMovieGenreReceived,this::OnError);
    }
    @Override
    public void loadMore(){
        //nothing
    }
    public void OnMovieGenreReceived(PersonMovieCredits personMovieCredits){
        personDetailView.binMovieCastPerson(personMovieCredits.getCast());
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }

}
