package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.PersonPopular;
import com.gomind.data.entities.PersonPopulars;
import com.gomind.domain.GetPersonPopular;
import com.gomind.gmivideo.vmp.view.PersonPopularView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class PersonPopularPresenter implements Presenter {
    private GetPersonPopular getPersonPopular;
    private Subscription mPersonPopular;
    private PersonPopularView personPopularView;
    private List<PersonPopular> personPopulars=new ArrayList<>();
    private double page=1;

    @Inject
    public PersonPopularPresenter(GetPersonPopular getPersonPopular) {
        this.getPersonPopular = getPersonPopular;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mPersonPopular.unsubscribe();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        personPopularView = (PersonPopularView) v;
    }

    @Override
    public void onCreate() {
        getPersonPopular.setPage(1);
        mPersonPopular=getPersonPopular.execute().subscribe(this::OnReceived,this::OnError);
    }
    @Override
    public void loadMore(){
        getPersonPopular.setPage(++page);
        mPersonPopular=getPersonPopular.execute().subscribe(this::OnMovieGenreReceivedMore, this::OnErrorMore);
    }
    public void OnReceived(PersonPopulars populars){
        personPopulars.addAll(populars.getResults());
        personPopularView.binPerson(personPopulars);
    }
    public void OnError(Throwable error){
        error.printStackTrace();
    }

    public void OnMovieGenreReceivedMore(PersonPopulars populars){
        personPopulars.addAll(populars.getResults());
        personPopularView.countLoadMore((int)populars.getTotal_results());
    }
    public void OnErrorMore(Throwable error){
        page--;
        personPopularView.error(error);
    }
}
