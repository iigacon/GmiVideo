package com.gomind.gmivideo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gomind.data.entities.Review;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieReviewComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieReViewModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.adapter.ReviewAdapter;
import com.gomind.gmivideo.vmp.presenter.MovieReviewPresenter;
import com.gomind.gmivideo.vmp.view.MovieReviewView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity implements MovieReviewView {
    @BindView(R.id.recycler_review)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_review)
    Toolbar toolbar;
    ReviewAdapter adapter;
    @Inject
    MovieReviewPresenter reviewPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        String idMovie=getIntent().getStringExtra("movie.id");
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getApplication();
        DaggerMovieReviewComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(gmiVideoApplication.getAppComponent())
                .movieReViewModule(new MovieReViewModule(idMovie))
                .build().inject(this);
        reviewPresenter.attachView(this);
        reviewPresenter.onCreate();
    }

    @Override
    public void bindReview(List<Review> reviews) {
        System.out.println("review count:"+reviews.size());
        if(reviews!=null){
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            adapter=new ReviewAdapter(reviews,this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addOnScrollListener(onScrollListener);
        }
    }
    private RecyclerView.OnScrollListener onScrollListener=new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return  false;
            }
            default:return false;
        }
    }
}
