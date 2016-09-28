package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.MovieLists;
import com.gomind.domain.GetAccount;
import com.gomind.domain.GetMovieCatelogy;
import com.gomind.gmivideo.vmp.view.MovieCatelogyView;
import com.gomind.gmivideo.vmp.view.View;

import javax.inject.Inject;

import rx.Subscription;


public class MovieCatelogyPresenter implements Presenter {

    private GetMovieCatelogy getMovieCatelogy;
    private MovieCatelogyView movieCatelogyView;
    private GetAccount getAccount;
    private Subscription mMovieCatelogy;
    private String session_id;
    @Inject
    public MovieCatelogyPresenter(GetMovieCatelogy getMovieCatelogy, GetAccount getAccount) {
        this.getMovieCatelogy = getMovieCatelogy;
        this.getAccount = getAccount;
    }

    @Override
    public void onStart() {


    }

    @Override
    public void onStop() {
        mMovieCatelogy.unsubscribe();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        movieCatelogyView = (MovieCatelogyView) v;
    }

    @Override
    public void onCreate() {
        mMovieCatelogy = getMovieCatelogy.execute().subscribe(this::OnMovieCatelogyReceived, this::OnError);
        getAccount.setSession_id(session_id);
        getAccount.execute().subscribe(this::OnReceivedAccount, this::OnError);
    }
    @Override
    public void loadMore() {

    }
    public void OnReceivedAccount(Account account){
        movieCatelogyView.bindAccount(account);
    }
    public void OnMovieCatelogyReceived(MovieLists movieLists) {
        movieCatelogyView.bindMovieCatelogy(movieLists.getGenres());
    }

    public void OnError(Throwable error) {
        error.printStackTrace();
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
