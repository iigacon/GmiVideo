package com.gomind.gmivideo.view.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gomind.data.entities.Account;
import com.gomind.data.entities.MovieList;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieCatelogyComponent;
import com.gomind.gmivideo.Injector.Module.AccountModule;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieCatelogyModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.Fragment.FragmentMovieCatalog;
import com.gomind.gmivideo.view.Fragment.FragmentMovieNowPlaying;
import com.gomind.gmivideo.view.Fragment.FragmentMoviePopular;
import com.gomind.gmivideo.view.Fragment.FragmentMovieTopRate;
import com.gomind.gmivideo.view.Fragment.FragmentMovieUpComing;
import com.gomind.gmivideo.view.Fragment.FragmentPersonPopular;
import com.gomind.gmivideo.view.Fragment.FragmentWatchListMovie;
import com.gomind.gmivideo.vmp.presenter.MovieCatelogyPresenter;
import com.gomind.gmivideo.vmp.ulti.AccountStatus;
import com.gomind.gmivideo.vmp.view.MovieCatelogyView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MovieCatelogyView{

    @Inject
    MovieCatelogyPresenter movieCatelogyPresenter;

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    private List<MovieList> movieLists;
    private AccountHeader headerResult;
    private String session_id;
    private String user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        session_id=getIntent().getStringExtra("session_id");
        new DrawerBuilder().withActivity(this).build();
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initializeDependencyInjector();
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.navigation)
                .build();
        drawer=new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggleAnimated(true)
                .withSliderBackgroundColor(Color.parseColor("#424242"))
                .withAccountHeader(headerResult)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    newFragment=null;
                    switch ((int) drawerItem.getIdentifier()){
                        case 1:{
                            getSupportActionBar().setTitle("GmiVideo");
                            break;
                        }
                        case 2:{
                            getSupportActionBar().setTitle("UpComing");
                            newFragment=new FragmentMovieUpComing();
                            break;
                        }
                        case 3:{
                            getSupportActionBar().setTitle("Now Playing");
                            newFragment=new FragmentMovieNowPlaying();
                            break;
                        }
                        case 4:{
                            getSupportActionBar().setTitle("Popular");
                            newFragment= new FragmentMoviePopular();
                            break;
                        }
                        case 5:{
                            getSupportActionBar().setTitle("Top Rate");
                            newFragment=new FragmentMovieTopRate();
                            break;
                        }
                        case 6:{
                            getSupportActionBar().setTitle("Peoples");
                            newFragment=new FragmentPersonPopular();
                            break;
                        }
                        case 7:{
                            getSupportActionBar().setTitle("Watchlist");
                            newFragment= FragmentWatchListMovie.newInstance(user_id, session_id);
                        }
                        default:
                            if(drawerItem.getIdentifier()>=100 &&drawerItem.getIdentifier()<200){
                                newFragment= FragmentMovieCatalog.newInstance(movieLists.get((int) drawerItem.getIdentifier()-100).getId());
                                getSupportActionBar().setTitle(movieLists.get((int) drawerItem.getIdentifier()-100).getName());
                            }

                    }
                    if(newFragment!=null) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction;
                        fragmentTransaction = fragmentManager.beginTransaction();
                        if (currentFragment != null) {
                            fragmentTransaction.remove(currentFragment);
                        }
                        fragmentTransaction.add(R.id.fragment_movie_catelogy, newFragment);
                        fragmentTransaction.commit();
                        if (currentFragment != null) {
                            currentFragment.onDestroy();
                        }
                        currentFragment = newFragment;

                    }
                    return false;
                })
                .build();

        drawer.addItem(new PrimaryDrawerItem().withIdentifier(1).withName("Home").withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));
        drawer.addItem(new PrimaryDrawerItem().withIdentifier(2).withName("UpComing").withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));
        drawer.addItem(new PrimaryDrawerItem().withIdentifier(3).withName("Now Playing").withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));
        drawer.addItem(new PrimaryDrawerItem().withIdentifier(4).withName("Popular").withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));
        drawer.addItem(new PrimaryDrawerItem().withIdentifier(5).withName("Top Rate").withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));

    }

    @Override
    public void onStart() {
        super.onStart();
    }
    private void initializeDependencyInjector() {
        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();
        DaggerMovieCatelogyComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(avengersApplication.getAppComponent())
                .movieCatelogyModule(new MovieCatelogyModule())
                .accountModule(new AccountModule())
                .build().inject(this);
        movieCatelogyPresenter.setSession_id(session_id);
        movieCatelogyPresenter.attachView(this);
        movieCatelogyPresenter.onCreate();
    }
    Fragment currentFragment;
    Fragment newFragment;
    Drawer drawer;
    @Override
    public void bindMovieCatelogy(List<MovieList> movieLists) {
        this.movieLists=movieLists;
        List<IDrawerItem> drawerItemsCatelogy=new ArrayList<>();
        int i=100;
        for(MovieList m: movieLists){
            drawerItemsCatelogy.add(new SecondaryDrawerItem().withLevel(2).withIdentifier(i++).withName(m.getName()).withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));
        }
        drawer.addItem(new DividerDrawerItem());
        drawer.addItem(new ExpandableDrawerItem().withName("Catelogies").withTextColor(Color.WHITE).withSelectable(false).withSubItems(drawerItemsCatelogy).withArrowColor(Color.WHITE));
        drawer.addItem(new DividerDrawerItem());
        drawer.addItem(new PrimaryDrawerItem().withIdentifier(6).withName("Peoples").withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));
        drawer.addItem(new DividerDrawerItem());
        drawer.addItem(new PrimaryDrawerItem().withIdentifier(7).withName("Watchlist").withTextColor(Color.parseColor("#ffffff")).withSelectedColor(Color.parseColor("#50c2c2c2")).withSelectedTextColor(Color.YELLOW).withSelectedIconColor(Color.YELLOW));
    }

    @Override
    public void bindAccount(Account account) {
        headerResult.addProfiles(new ProfileDrawerItem().withName(account.getUsername()));
        user_id=account.getId();
        AccountStatus.user_id=user_id;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    if (drawer.getDrawerItem(6).isSelected()) {
                        SearchPersonActivity.start(getBaseContext(), query);
                    } else SearchMovieActivity.start(getBaseContext(), query);
                }catch (Exception e){}
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                drawer.openDrawer();
                return false;
            }
            default:return false;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen()){
            drawer.closeDrawer();
        }else {
            super.onBackPressed();
        }
    }
    public static void start(Context context, String session_id){
        Intent intent=new Intent(context, MainActivity.class);
        intent.putExtra("session_id", session_id);
        context.startActivity(intent);
    }
}
