package com.gomind.gmivideo.vmp.presenter;

import com.gomind.data.entities.PersonPopular;
import com.gomind.data.entities.PersonPopulars;
import com.gomind.domain.SearchPerson;
import com.gomind.gmivideo.vmp.view.SearchPersonView;
import com.gomind.gmivideo.vmp.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

public class SearchPersonPresenter implements Presenter{
    private SearchPerson searchPerson;
    private SearchPersonView searchPersonView;
    private Subscription mSearchPerson;
    private List<PersonPopular> personPopularList=new ArrayList<>();
    private String query;
    private int page;
    @Inject
    public SearchPersonPresenter(SearchPerson searchPerson) {
        this.searchPerson = searchPerson;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mSearchPerson.unsubscribe();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(View v) {
        searchPersonView= (SearchPersonView) v;
    }

    @Override
    public void onCreate() {
        page=1;
        personPopularList.clear();
        if(!"".equals(query)) {
            searchPerson.setQuery(query);
            searchPerson.setPage(page);
            mSearchPerson = searchPerson.execute().subscribe(this::OnReceived, this::OnError);
        }
    }

    @Override
    public void loadMore() {
        searchPerson.setPage(page);
        mSearchPerson = searchPerson.execute().subscribe(this::OnReceivedMore, this::OnError);

    }
    public void onQuery(String query){
        this.query=query;
    }
    public void OnReceived(PersonPopulars personPopulars){
        personPopularList.addAll(personPopulars.getResults());
        searchPersonView.binPerson(personPopularList);
        page++;
    }
    public void OnError(Throwable e){
        searchPersonView.error(e);
    }

    public void OnReceivedMore(PersonPopulars personPopulars){
        personPopularList.addAll(personPopulars.getResults());
        searchPersonView.countLoadMore(personPopulars.getResults().size());
        page++;
    }
}
