package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.ImagePersons;
import com.gomind.domain.GetImagePerson;
import com.gomind.gmivideo.vmp.view.PersonDetailView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;

public class PersonImagePresenter implements Presenter {
    private GetImagePerson getImagePerson;
    private Subscription mMovieGenre;
    private PersonDetailView personDetailView;


    @Inject
    public PersonImagePresenter(GetImagePerson getImagePerson) {
        this.getImagePerson = getImagePerson;
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
        mMovieGenre=getImagePerson.execute().subscribe(this::OnMovieGenreReceived,this::OnError);
    }
    @Override
    public void loadMore(){
        //nothing
    }
    public void OnMovieGenreReceived(ImagePersons imagePersons){
        personDetailView.binImageProfile(imagePersons.getProfiles());
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }

}
