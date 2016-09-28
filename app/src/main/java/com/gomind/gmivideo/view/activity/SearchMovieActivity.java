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

import com.gomind.data.entities.Movie;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieSearchComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieSearchModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.adapter.MovieCatelogyAdapter;
import com.gomind.gmivideo.vmp.presenter.SearchMoviePresenter;
import com.gomind.gmivideo.vmp.view.MovieBaseView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieActivity extends AppCompatActivity implements MovieBaseView {

    @Inject
    SearchMoviePresenter searchMoviePresenter;
    @BindView(R.id.recycler_movie)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_search_movie)
    Toolbar toolbar;
    private MovieCatelogyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        String query = getIntent().getStringExtra("search.query");
        initializeDependencyInjector(query);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFitsSystemWindows(true);
        recyclerView.addOnScrollListener(mOnScrollListener);
    }
    private void initializeDependencyInjector(String query) {

        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();
        DaggerMovieSearchComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(avengersApplication.getAppComponent())
                .movieSearchModule(new MovieSearchModule())
                .build().inject(this);
        if(query!=null){
            searchMoviePresenter.onQuery(query);
            searchMoviePresenter.attachView(this);
            searchMoviePresenter.onCreate();
        }
    }


    @Override
    public void bindMovieBase(List<Movie> movies) {
        adapter=new MovieCatelogyAdapter(movies,this,(position, idMovie) -> MovieDetailActivity.start(this,idMovie), (id, imageView) -> {});
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void bindLoadMore(int count) {
        adapter.notifyItemRangeInserted(adapter.getItemCount()+count,count);
    }

    @Override
    public void showDetailMovie(String id) {

    }
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            int visiableItemsCount = layoutManager.getChildCount();
            int totalItemsCount = layoutManager.getItemCount();
            int firstVisiableItemPos = layoutManager.findFirstVisibleItemPosition();
            if ((visiableItemsCount + firstVisiableItemPos + 16) >= totalItemsCount) {
                searchMoviePresenter.loadMore();
            }
        }
    };

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!"".equals(newText)) {
                    searchMoviePresenter.onQuery(newText);
                    searchMoviePresenter.onCreate();
                }
                return true;
            }
        });
        return  true;
    }
    public static void start(Context context, String query){
        Intent intent=new Intent(context, SearchMovieActivity.class);
        intent.putExtra("search.query", query);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
