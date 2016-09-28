package com.gomind.gmivideo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gomind.data.entities.PersonPopular;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerSearchPersonComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.SearchPersonModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.adapter.PersonPopularAdapter;
import com.gomind.gmivideo.vmp.presenter.SearchPersonPresenter;
import com.gomind.gmivideo.vmp.view.SearchPersonView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPersonActivity extends AppCompatActivity implements SearchPersonView{
    private String query;
    @BindView(R.id.recycler_movie)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_search_movie)
    Toolbar toolbar;
    @Inject
    SearchPersonPresenter searchPersonPresenter;
    private PersonPopularAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        query = getIntent().getStringExtra("search.query");
        initializeDependencyInjector(query);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        GridLayoutManager layoutManager=new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFitsSystemWindows(true);
        recyclerView.addOnScrollListener(mOnScrollListener);
    }
    private void initializeDependencyInjector(String query) {

        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();
        DaggerSearchPersonComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(avengersApplication.getAppComponent())
                .searchPersonModule(new SearchPersonModule())
                .build().inject(this);
        if(!"".equals(query)){
            searchPersonPresenter.onQuery(query);
            searchPersonPresenter.attachView(this);
            searchPersonPresenter.onCreate();
        }
    }

    @Override
    public void binPerson(List<PersonPopular> persons) {
        adapter=new PersonPopularAdapter(persons, this, id -> PersonDetailActivity.start(this,id));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void countLoadMore(int count) {
        adapter.notifyItemRangeInserted(adapter.getItemCount()+count,count);
    }

    @Override
    public void error(Throwable error) {
        error.printStackTrace();
    }
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            int visiableItemsCount = layoutManager.getChildCount();
            int totalItemsCount = layoutManager.getItemCount();
            int firstVisiableItemPos = layoutManager.findFirstVisibleItemPosition();
            if ((visiableItemsCount + firstVisiableItemPos + 16) >= totalItemsCount) {
                searchPersonPresenter.loadMore();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!"".equals(newText)) {
                    searchPersonPresenter.onQuery(newText);
                    searchPersonPresenter.onCreate();

                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public static void start(Context context, String query){
        Intent intent=new Intent(context, SearchPersonActivity.class);
        intent.putExtra("search.query", query);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
