package com.gomind.gmivideo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.ImagePerson;
import com.gomind.data.entities.Person;
import com.gomind.data.entities.PersonMovieCredit;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerPersonDetailComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.ImagePersonModule;
import com.gomind.gmivideo.Injector.Module.MovieCastPersonModule;
import com.gomind.gmivideo.Injector.Module.PersonModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.adapter.MovieCastPersonAdapter;
import com.gomind.gmivideo.view.view.ImageViewTopCrop;
import com.gomind.gmivideo.vmp.presenter.MovieCastPersonPresenter;
import com.gomind.gmivideo.vmp.presenter.PersonImagePresenter;
import com.gomind.gmivideo.vmp.presenter.PersonPresenter;
import com.gomind.gmivideo.vmp.view.PersonDetailView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonDetailActivity extends AppCompatActivity implements PersonDetailView{
    @BindView(R.id.image_background_people)
    ImageViewTopCrop image_background;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.image_profile)
    ImageView image_profile;
    @BindView(R.id.title_name)
    TextView title_name;
    @BindView(R.id.person_info)
    TextView person_info;
    @BindView(R.id.date_born)
    TextView data_born;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movie_cast_recycler)
    RecyclerView recyclerView;
    @Inject
    PersonPresenter personPresenter;
    @Inject
    PersonImagePresenter imagePresenter;
    @Inject
    MovieCastPersonPresenter castPersonPresenter;
    MovieCastPersonAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        String id=getIntent().getStringExtra("person.id");
        GmiVideoApplication application = (GmiVideoApplication) getApplication();
        DaggerPersonDetailComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(application.getAppComponent())
                .personModule(new PersonModule(id))
                .imagePersonModule(new ImagePersonModule(id))
                .movieCastPersonModule(new MovieCastPersonModule(id))
                .build().inject(this);
        personPresenter.attachView(this);
        personPresenter.onCreate();
        imagePresenter.attachView(this);
        imagePresenter.onCreate();
        castPersonPresenter.attachView(this);
        castPersonPresenter.onCreate();
    }

    @Override
    public void binPerson(Person person) {
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w300"+person.getProfile_path())
                .error(R.drawable.ahihi)
                .into(image_profile);
        collapsingToolbarLayout.setTitle(person.getName());
        title_name.setText(person.getName());
        person_info.setText(person.getBiography());
        data_born.setText("Born:\n"+person.getBirthday()+"\n"+person.getPlace_of_birth());
    }

    @Override
    public void binMovieCastPerson(List<PersonMovieCredit> movieCredit) {
        adapter=new MovieCastPersonAdapter(movieCredit,this,id -> MovieDetailActivity.start(this,id));
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFitsSystemWindows(true);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void binImageProfile(List<ImagePerson> imagePersons) {
        if(imagePersons!=null){

            if(imagePersons.size()>1) {
                Glide.with(this)
                        .load("http://image.tmdb.org/t/p/w500" + imagePersons.get(1).getFile_path())
                        .placeholder(R.drawable.ahihi)
                        .error(R.drawable.ahihi)
                        .into(image_background);

            }
            else
            if(imagePersons.size()>0) {
                Glide.with(this)
                        .load("http://image.tmdb.org/t/p/w500" + imagePersons.get(0).getFile_path())
                        .placeholder(R.drawable.ahihi)
                        .error(R.drawable.ahihi)
                        .into(image_background);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchPersonActivity.start(getBaseContext(),query);
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
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return  false;
            }
            default:return false;
        }
    }
    public static void start(Context context, String id){
        Intent intent=new Intent(context, PersonDetailActivity.class);
        intent.putExtra("person.id", id);
        context.startActivity(intent);
    }
}
