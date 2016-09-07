package com.gomind.gmivideo.view.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.Company;
import com.gomind.data.entities.Credits;
import com.gomind.data.entities.Genres;
import com.gomind.data.entities.Image;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MovieSimilar;
import com.gomind.data.entities.Video;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieDetailComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieCreditModule;
import com.gomind.gmivideo.Injector.Module.MovieDetailModule;
import com.gomind.gmivideo.Injector.Module.MovieImageModule;
import com.gomind.gmivideo.Injector.Module.MovieSimilarModule;
import com.gomind.gmivideo.Injector.Module.MovieVideoModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.Fragment.FragmentMovieImage;
import com.gomind.gmivideo.view.adapter.CastAdapter;
import com.gomind.gmivideo.view.adapter.CrewAdapter;
import com.gomind.gmivideo.view.adapter.MovieSimilarAdapter;
import com.gomind.gmivideo.vmp.presenter.MovieCreditPresenter;
import com.gomind.gmivideo.vmp.presenter.MovieDetailPresenter;
import com.gomind.gmivideo.vmp.presenter.MovieImagePresenter;
import com.gomind.gmivideo.vmp.presenter.MovieSimilarPresenter;
import com.gomind.gmivideo.vmp.presenter.MovieVideoPresenter;
import com.gomind.gmivideo.vmp.view.MovieDetailView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Duc on 9/4/16.
 */
public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView{
    private String idMovie;
    private List<Video> videos;
    @Inject MovieDetailPresenter movieDetailPresenter;
    @Inject
    MovieSimilarPresenter movieSimilarPresenter;
    @Inject
    MovieImagePresenter movieImagePresenter;
    @Inject
    MovieVideoPresenter movieVideoPresenter;
    @Inject
    MovieCreditPresenter movieCreditPresenter;
    private MovieDetail movieDetail;
//    private ImageView imageView;
    private TextView movie_info;
    private TextView play_trailer;
    private CollapsingToolbarLayout collapsingToolbar;
    private ViewPager viewPager;
    private List<Image> images;
    private TextView rate;
    private TextView runtime;
    private TextView review;
    private ImageView image_poster;
    private TextView movieName;
    @BindView(R.id.movie_genre)
    TextView movie_genre;
    @BindView(R.id.movie_tagline)
    TextView movie_tagline;
    @BindView(R.id.image_videos)
    ImageView image_videos;
    @BindView(R.id.image_images)
    ImageView image_images;
    @BindView(R.id.count_images)
    TextView count_images;
    @BindView(R.id.count_video)
    TextView count_videos;
    @BindView(R.id.movie_cast_recycler)
    RecyclerView recyclerView_Cast;
    @BindView(R.id.movie_crew_recycler)
    RecyclerView recyclerView_Crew;
    @BindView(R.id.movie_similar_recycler)
    RecyclerView recyclerView_Similar;
    @BindView(R.id.movie_revenue) TextView revenue;
    @BindView(R.id.movie_company) TextView company;
    @BindView(R.id.movie_budget) TextView budget;
    MovieSimilarAdapter similarAdapter;
    CastAdapter castAdapter;
    CrewAdapter crewAdapter;
    private String key_youtube;
    private List<com.gomind.gmivideo.view.activity.Image> images_fake;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ButterKnife.bind(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idMovie=getIntent().getStringExtra("movie.id");
        initilizeInjector();
//        imageView= (ImageView) findViewById(R.id.backdrop);
        movie_info= (TextView) findViewById(R.id.movie_info);
        play_trailer= (TextView) findViewById(R.id.play_trailer);
        play_trailer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play, 0, 0, 0);
        play_trailer.setOnClickListener(v->play());
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextColor(Color.TRANSPARENT);
        collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        movieName= (TextView) findViewById(R.id.title_movie);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        image_poster= (ImageView) findViewById(R.id.movie_poster);
        rate= (TextView) findViewById(R.id.movie_rate);
        rate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rate,0,0,0);
        review= (TextView) findViewById(R.id.movie_review);
        review.setCompoundDrawablesWithIntrinsicBounds(R.drawable.date,0,0,0);
        runtime= (TextView) findViewById(R.id.movie_runtime);
        runtime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer,0,0,0);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Cast.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Crew.setLayoutManager(layoutManager2);
        LinearLayoutManager layoutManager3
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Similar.setLayoutManager(layoutManager3);
    }
    public void play(){
        Intent characterDetailItent = new Intent(this, PlayerViewDemoActivity.class);
        characterDetailItent.putExtra("idYoutube", key_youtube);
        startActivity(characterDetailItent);
    }
    public static void start(Context context, String movieId) {
        Intent characterDetailItent = new Intent(context, MovieDetailActivity.class);
        characterDetailItent.putExtra("movie.id", movieId);
        context.startActivity(characterDetailItent);
    }

    private void initilizeInjector(){
        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();
        DaggerMovieDetailComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(avengersApplication.getAppComponent())
                .movieDetailModule(new MovieDetailModule(idMovie))
                .movieImageModule(new MovieImageModule(idMovie))
                .movieSimilarModule(new MovieSimilarModule(0,idMovie))
                .movieVideoModule(new MovieVideoModule(idMovie))
                .movieCreditModule(new MovieCreditModule(idMovie))
                .build().inject(this);
        movieDetailPresenter.attachView(this);
        movieDetailPresenter.onCreate();
        movieSimilarPresenter.attachView(this);
        movieSimilarPresenter.onCreate();
        movieImagePresenter.attachView(this);
        movieImagePresenter.onCreate();
        movieVideoPresenter.attachView(this);
        movieVideoPresenter.onCreate();
        movieCreditPresenter.attachView(this);
        movieCreditPresenter.onCreate();
    }
    @Override
    public void bindMovieDetail(MovieDetail movieDetail) {
        this.movieDetail=movieDetail;
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w500"+movieDetail.getPoster_path())
                .crossFade()
                .into(image_poster);
        movieName.setText(movieDetail.getTitle());
        movie_info.setText(movieDetail.getOverview());

        rate.setText(String.valueOf(movieDetail.getVote_average())+"/10\n"+String.valueOf(movieDetail.getVote_count()));
        runtime.setText("Runtime\n"+String.valueOf(movieDetail.getRuntime())+" min");
        review.setText("Release Date\n"+String.valueOf(movieDetail.getRelease_date()));
        String genre="";

        movie_tagline.setText(movieDetail.getTagline());
        revenue.setText(movieDetail.getRevenue());
        budget.setText(movieDetail.getBudget());
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w500"+movieDetail.getBackdrop_path())
                .crossFade()
                .into(image_images);
        for(Genres s:movieDetail.getGenres()){
            genre+=s.getName()+", ";
        }
        movie_genre.setText(genre);
        String companyString="";
        for(Company c:movieDetail.getProduction_companies()){
            companyString+=c.getName()+", ";
        }
        company.setText(companyString);
    }

    @Override
    public void bindMovieSimilar(List<MovieSimilar> movieSimilars) {
        similarAdapter=new MovieSimilarAdapter(movieSimilars,this,((position, idMovie1) -> {
            MovieDetailActivity.start(this,idMovie1);
            Toast.makeText(MovieDetailActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        }));
        recyclerView_Similar.setAdapter(similarAdapter);
    }

    @Override
    public void bindMovieImage(List<Image> images) {
        this.images=images;
        Adapter adapter = new Adapter(getSupportFragmentManager());
        for(Image m: images){
            adapter.addFragment(new FragmentMovieImage().instance("http://image.tmdb.org/t/p/w780"+m.getFile_path()));
        }
        viewPager.setAdapter(adapter);
        pageSwitcher(10);
        count_images.setText(("Images("+images.size()+1+")"));
        images_fake=images;
        image_images.setOnClickListener(v->{
            ImageShowActivity.start(this,images);
        });
        count_images.setOnClickListener(v->{
            ImageShowActivity.start(this, images);
        });
    }

    @Override
    public void playYoutubeTrailer(List<Video> videoTrailer) {
        for(Video v:videoTrailer){
            if(v.getType().equals("Trailer")){
                key_youtube=v.getKey();
                break;
            }
        }
        Glide.with(this)
                .load("http://img.youtube.com/vi/"+key_youtube+"/mqdefault.jpg")
                .crossFade()
                .into(image_videos);
        count_videos.setText("Videos("+videoTrailer.size()+1+")");
    }

    @Override
    public void bindCreditMovie(Credits credits) {
        castAdapter=new CastAdapter(credits.getCast(),this,(position, shareView, movieImageView) -> {
            Toast.makeText(MovieDetailActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        });
        recyclerView_Cast.setAdapter(castAdapter);
        crewAdapter=new CrewAdapter(credits.getCrew(),this,(position, idCrew) -> {
            Toast.makeText(MovieDetailActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        });
        recyclerView_Crew.setAdapter(crewAdapter);
    }

    @Override
    public void videoMovie(String idMovie) {

    }

    @Override
    public void imageMovie(String idMovie) {

    }
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
    Timer timer;

    public void pageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
    }

    class RemindTask extends TimerTask {
        boolean first=false;
        @Override
        public void run() {
            runOnUiThread(() -> {
                int page=viewPager.getCurrentItem();
                if(!viewPager.isShown()){
                    timer.cancel();
                }
                if(page==0&!first){
                    first=true;

                }else {
                    if (page >= images.size() - 1) {
                        page = 0;
                        viewPager.setCurrentItem(page);
                    } else {
                        page += 1;
                        viewPager.setCurrentItem(page);
                    }
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setBackgroundColor(Color.TRANSPARENT);

//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//        searchView.setBackgroundColor(Color.TRANSPARENT);
//        searchView.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return false;
            }
            case R.id.action_share:{
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v="+key_youtube);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
            default: return false;
        }
    }

}
