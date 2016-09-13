package com.gomind.gmivideo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gomind.gmivideo.R;

public class SearchActivity extends AppCompatActivity {

//    @Inject MovieSe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }
    private void initializeDependencyInjector() {
//        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();
//        DaggerMovieCatelogyComponent.builder()
//                .activityModule(new ActivityModule(this))
//                .appComponent(avengersApplication.getAppComponent())
//                .movieCatelogyModule(new MovieCatelogyModule())
//                .build().inject(this);
//        movieCatelogyPresenter.attachView(this);
//        movieCatelogyPresenter.onCreate();
    }
}
