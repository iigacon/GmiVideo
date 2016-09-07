package com.gomind.gmivideo.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.widget.Toast;

import com.gomind.data.entities.Movie;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMoviePopularComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MoviePopularModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.adapter.MoviePopularAdapter;
import com.gomind.gmivideo.vmp.presenter.MoviePopularPresenter;
import com.gomind.gmivideo.vmp.view.MoviePopularView;
import com.gomind.gmivideo.vmp.view.RecyclerInsetsDecoration;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MoviePopularView{

    @Inject MoviePopularPresenter moviePopularPresenter;
    private MoviePopularAdapter moviePopularAdapter;
    RecyclerView movieRecycler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new DrawerBuilder().withActivity(this).build();
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(R.drawable.home).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Movies").withIcon(R.drawable.movies).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("TV").withIcon(R.drawable.tv).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Celebs").withIcon(R.drawable.celebs).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("News").withIcon(R.drawable.news).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Your Watchlist").withIcon(R.drawable.favorite).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("Ratings").withIcon(R.drawable.rate2).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName("Your Lists").withIcon(R.drawable.list).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item9 = new PrimaryDrawerItem().withIdentifier(9).withName("Notifications").withIcon(R.drawable.notification).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item10 = new PrimaryDrawerItem().withIdentifier(10).withName("Check-Ins").withIcon(R.drawable.check).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        PrimaryDrawerItem item11 = new PrimaryDrawerItem().withIdentifier(11).withName("History").withIcon(R.drawable.history).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW);
        
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .build();
        new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundColor(Color.parseColor("#424242"))
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1, item2, item3, item4, item5,
                        new DividerDrawerItem(),
                        item6, item7, item8, item9, item10, item11
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                            Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
                            return true;
                })
                .build();
        movieRecycler = (RecyclerView) findViewById(R.id.activity_avengers_recycler);
        initializeDependencyInjector();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    private void initializeDependencyInjector() {
        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();

        DaggerMoviePopularComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(avengersApplication.getAppComponent())
                .moviePopularModule(new MoviePopularModule(0))
                .build().inject(this);
        moviePopularPresenter.attachView(this);
        moviePopularPresenter.onCreate();
        movieRecycler.setLayoutManager(new LinearLayoutManager(this));
        movieRecycler.addItemDecoration(new RecyclerInsetsDecoration(this));
        movieRecycler.addOnScrollListener(mOnScrollListener);
    }

    private OnScrollListener mOnScrollListener=new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            LinearLayoutManager layoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
            int visiableItemsCount=layoutManager.getChildCount();
            int totalItemsCount=layoutManager.getItemCount();
            int firstVisiableItemPos=layoutManager.findFirstVisibleItemPosition();
            if((visiableItemsCount+firstVisiableItemPos+8)>=totalItemsCount){
                moviePopularPresenter.askNewForMoviePopulars();
            }
        }
    };

    @Override
    public void bindMoviePopular(List<Movie> movies) {
        moviePopularAdapter = new MoviePopularAdapter(movies, this, (position, sharedView, characterImageView) -> moviePopularPresenter.onElementClick(position));
        movieRecycler.setAdapter(moviePopularAdapter);
    }

    @Override
    public void showCharacterList() {

    }

    @Override
    public void hideCharactersList() {

    }

    @Override
    public void showLoadingMoreCharactersIndicator() {

    }

    @Override
    public void hideLoadingMoreCharactersIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showLightError() {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showEmptyIndicator() {

    }

    @Override
    public void hideEmptyIndicator() {

    }

    @Override
    public void updateMoviePopularList(int charactersLimit) {
        moviePopularAdapter.notifyItemRangeInserted(moviePopularAdapter.getItemCount()+charactersLimit,charactersLimit);
    }


    @Override
    public void showConnectionErrorMessage() {

    }

    @Override
    public void showServerErrorMessage() {

    }

    @Override
    public void showUknownErrorMessage() {

    }

    @Override
    public void showDetailMovie(String movieId) {
        MovieDetailActivity.start(this,movieId);
    }

}
