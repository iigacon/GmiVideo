package com.gomind.gmivideo.view.activity;


import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.gomind.data.entities.PostWatchList;
import com.gomind.data.entities.Video;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieDetailComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieCreditModule;
import com.gomind.gmivideo.Injector.Module.MovieDetailModule;
import com.gomind.gmivideo.Injector.Module.MovieImageModule;
import com.gomind.gmivideo.Injector.Module.MovieSimilarModule;
import com.gomind.gmivideo.Injector.Module.MovieVideoModule;
import com.gomind.gmivideo.Injector.Module.PostWatchListModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.mapper.MapperData;
import com.gomind.gmivideo.view.Fragment.FragmentMovieImage;
import com.gomind.gmivideo.view.adapter.CastAdapter;
import com.gomind.gmivideo.view.adapter.CrewAdapter;
import com.gomind.gmivideo.view.adapter.MovieSimilarAdapter;
import com.gomind.gmivideo.view.ulti.MessageDetailCreated;
import com.gomind.gmivideo.view.ulti.WatchlistMovie;
import com.gomind.gmivideo.view.ulti.ultils;
import com.gomind.gmivideo.vmp.presenter.MovieCreditPresenter;
import com.gomind.gmivideo.vmp.presenter.MovieDetailPresenter;
import com.gomind.gmivideo.vmp.presenter.MovieImagePresenter;
import com.gomind.gmivideo.vmp.presenter.MovieSimilarPresenter;
import com.gomind.gmivideo.vmp.presenter.MovieVideoPresenter;
import com.gomind.gmivideo.vmp.presenter.PostWatchListPresenter;
import com.gomind.gmivideo.vmp.ulti.AccountStatus;
import com.gomind.gmivideo.vmp.view.MovieDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {
    private String idMovie;
    @Inject
    MovieDetailPresenter movieDetailPresenter;
    @Inject
    MovieSimilarPresenter movieSimilarPresenter;
    @Inject
    MovieImagePresenter movieImagePresenter;
    @Inject
    MovieVideoPresenter movieVideoPresenter;
    @Inject
    MovieCreditPresenter movieCreditPresenter;
    @Inject
    PostWatchListPresenter postWatchListPresenter;
    @BindView(R.id.movie_info)
    TextView movie_info;

    @BindView(R.id.play_trailer)
    TextView play_trailer;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.movie_rate)
    TextView rate;

    @BindView(R.id.movie_runtime)
    TextView runtime;

    @BindView(R.id.movie_review)
    TextView review;

    @BindView(R.id.movie_poster)
    ImageView image_poster;

    @BindView(R.id.title_movie)
    TextView movieName;

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

    @BindView(R.id.movie_revenue)
    TextView revenue;

    @BindView(R.id.movie_company)
    TextView company;

    @BindView(R.id.movie_budget)
    TextView budget;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.review)
    TextView review_button;


    @OnClick(R.id.float_favoriteplus)
    void addWatchList(){
        postWatchListPresenter.setMedia_id(idMovie);
        postWatchListPresenter.onCreate();
        if(!WatchlistMovie.check(idMovie)) {
            floatingActionButton.setImageResource(R.drawable.bookmark_check);
            WatchlistMovie.add(idMovie);
        }
        else{
            floatingActionButton.setImageResource(R.drawable.favoriteplus);
            WatchlistMovie.remove(idMovie);
        }
    }
    @BindView(R.id.float_favoriteplus)
    FloatingActionButton floatingActionButton;

    MovieSimilarAdapter similarAdapter;
    CastAdapter castAdapter;
    CrewAdapter crewAdapter;
    private String key_youtube;
    private List<Image> images;
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManager2;
    LinearLayoutManager layoutManager3;
    private int count_create = 0;
    private List<com.gomind.gmivideo.view.activity.Image> images_fake;
    private UUID currentId = UUID.randomUUID();

    @Subscribe
    public void onEvent(MessageDetailCreated message) {
        if (!message.getUUID().equals(currentId)) {
            count_create += 1;
        }
        if (count_create >= 4) {
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        EventBus.getDefault().post(new MessageDetailCreated(UUID.randomUUID()));
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        idMovie = getIntent().getStringExtra("movie.id");
        initilizeInjector();
        play_trailer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play, 0, 0, 0);
        play_trailer.setOnClickListener(v -> play());
        collapsingToolbar.setCollapsedTitleTextColor(Color.TRANSPARENT);
        collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        rate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rate, 0, 0, 0);
        review.setCompoundDrawablesWithIntrinsicBounds(R.drawable.date, 0, 0, 0);
        runtime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer, 0, 0, 0);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        review_button.setOnClickListener(v -> {
            if (idMovie != null) {
                Intent intent = new Intent(this, ReviewActivity.class);
                intent.putExtra("movie.id", idMovie);
                startActivity(intent);
            }
        });
        if(WatchlistMovie.check(idMovie)) {
            floatingActionButton.setImageResource(R.drawable.bookmark_check);
        }
        else{
            floatingActionButton.setImageResource(R.drawable.favoriteplus);
        }
    }

    public void play() {
        if(key_youtube!=null) {
            Intent characterDetailItent = new Intent(this, PlayerViewDemoActivity.class);
            characterDetailItent.putExtra("idYoutube", key_youtube);
            startActivity(characterDetailItent);
        }else {
            Toast.makeText(MovieDetailActivity.this, "Ooh! This film does not have trailer.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void start(Context context, String movieId) {
        if(movieId!=null) {
            Intent characterDetailItent = new Intent(context, MovieDetailActivity.class);
            characterDetailItent.putExtra("movie.id", movieId);
            context.startActivity(characterDetailItent);
        }
    }

    private void initilizeInjector() {
        GmiVideoApplication avengersApplication = (GmiVideoApplication) getApplication();
        DaggerMovieDetailComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(avengersApplication.getAppComponent())
                .movieDetailModule(new MovieDetailModule(idMovie))
                .movieImageModule(new MovieImageModule(idMovie))
                .movieSimilarModule(new MovieSimilarModule(idMovie))
                .movieVideoModule(new MovieVideoModule(idMovie))
                .movieCreditModule(new MovieCreditModule(idMovie))
                .postWatchListModule(new PostWatchListModule())
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
        postWatchListPresenter.attachView(this);
        postWatchListPresenter.setId(AccountStatus.user_id);
        postWatchListPresenter.setSession_id(AccountStatus.session_id);
    }

    @Override
    public void bindMovieDetail(MovieDetail movieDetail) {
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w342" + movieDetail.getPoster_path())
                .crossFade()
                .into(image_poster);
        movieName.setText(movieDetail.getTitle());
        movie_info.setText(movieDetail.getOverview());

        rate.setText(String.valueOf(movieDetail.getVote_average()) + "/10\n" + String.valueOf(movieDetail.getVote_count()));
        runtime.setText("Runtime\n" + ultils.convertHour(movieDetail.getRuntime()));
        review.setText("Release Date\n" + ultils.convertDate(movieDetail.getRelease_date()));
        String genre = "";

        movie_tagline.setText(movieDetail.getTagline());
        revenue.setText(movieDetail.getRevenue());
        budget.setText(movieDetail.getBudget());
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w300" + movieDetail.getBackdrop_path())
                .crossFade()
                .into(image_images);
        for (Genres s : movieDetail.getGenres()) {
            genre += s.getName() + ", ";
        }
        movie_genre.setText(genre);
        String companyString = "";
        for (Company c : movieDetail.getProduction_companies()) {
            companyString += c.getName() + ", ";
        }
        company.setText(companyString);
    }
    private boolean currentFavorite;
    private String currentIdMovie;
    @Override
    public void bindMovieSimilar(List<MovieSimilar> movieSimilars) {
        similarAdapter = new MovieSimilarAdapter(movieSimilars, this, ((position, idMovie1) -> MovieDetailActivity.start(this, idMovie1)),(id, imageView) -> {
            currentIdMovie=id;
            if(WatchlistMovie.check(id)){
                currentFavorite=true;
                postWatchListPresenter.setWatchlist(false);
            }else{
                postWatchListPresenter.setWatchlist(true);
                currentFavorite=false;
            }
            postWatchListPresenter.setMedia_id(id);
            postWatchListPresenter.onCreate();
        });
        recyclerView_Similar.setAdapter(similarAdapter);
        recyclerView_Similar.setLayoutManager(layoutManager3);
    }

    @Override
    public void bindMovieImage(List<Image> images) {
        this.images = images;
        Adapter adapter = new Adapter(getSupportFragmentManager());
        for (Image m : images) {
            adapter.addFragment(new FragmentMovieImage().instance("http://image.tmdb.org/t/p/w500" + m.getFile_path()));
        }
        viewPager.setAdapter(adapter);
        pageSwitcher(10);
        String count_image = "Images(" + String.valueOf(images.size()) + ")";
        count_images.setText(count_image);
        images_fake = MapperData.MapperImages(images);
        image_images.setOnClickListener(v -> ImageShowActivity.start(this, images_fake));
        count_images.setOnClickListener(v -> ImageShowActivity.start(this, images_fake));
    }

    @Override
    public void playYoutubeTrailer(List<Video> videoTrailer) {
        for (Video v : videoTrailer) {
            if (v.getType().equals("Trailer")) {
                key_youtube = v.getKey();
                break;
            }
        }
        if (key_youtube == null & videoTrailer.size() > 0) {
            key_youtube = videoTrailer.get(0).getKey();
        }
        Glide.with(this)
                .load("http://img.youtube.com/vi/" + key_youtube + "/mqdefault.jpg")
                .crossFade()
                .error(R.drawable.navigation)
                .into(image_videos);
        String count_video = "Videos(" + String.valueOf(videoTrailer.size()) + ")";
        count_videos.setText(count_video);
        count_videos.setOnClickListener(v -> VideoShowActivity.start(this, MapperData.MapperVideos(videoTrailer)));
        image_videos.setOnClickListener(v -> VideoShowActivity.start(this, MapperData.MapperVideos(videoTrailer)));
    }

    @Override
    public void bindCreditMovie(Credits credits) {
        castAdapter = new CastAdapter(credits.getCast(), this, id -> {
            Intent intent=new Intent(this, PersonDetailActivity.class);
            intent.putExtra("person.id",id);
            startActivity(intent);
        });
        recyclerView_Cast.setAdapter(castAdapter);
        recyclerView_Cast.setLayoutManager(layoutManager);
        crewAdapter = new CrewAdapter(credits.getCrew(), this, idCrew -> {
            Intent intent=new Intent(this, PersonDetailActivity.class);
            intent.putExtra("person.id", idCrew);
            startActivity(intent);
        });
        recyclerView_Crew.setAdapter(crewAdapter);
        recyclerView_Crew.setLayoutManager(layoutManager2);
    }

    @Override
    public void videoMovie(String idMovie) {

    }

    @Override
    public void imageMovie(String idMovie) {

    }

    @Override
    public void bindPostWatchList(PostWatchList postWatchList) {
        if(postWatchList.getStatus_code()==1){
            if(currentFavorite){
                WatchlistMovie.remove(currentIdMovie);
                Toast.makeText(MovieDetailActivity.this, "Watchlist removed!", Toast.LENGTH_SHORT).show();
            }else {
                WatchlistMovie.add(currentIdMovie);
                Toast.makeText(MovieDetailActivity.this, "Watchlist added!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

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
            return "";
        }
    }

    Timer timer;

    public void pageSwitcher(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
    }

    class RemindTask extends TimerTask {
        boolean first = false;

        @Override
        public void run() {
            runOnUiThread(() -> {
                int page = viewPager.getCurrentItem();
                if (!viewPager.isShown()) {
                    timer.cancel();
                }
                if (page == 0 & !first) {
                    first = true;

                } else {
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
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchMovieActivity.start(getBaseContext(),query);
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
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return false;
            }
            case R.id.action_share: {
                Glide.get(this).trimMemory(TRIM_MEMORY_COMPLETE);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=" + key_youtube);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
            default:
                return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onTrimMemory(ComponentCallbacks2.TRIM_MEMORY_MODERATE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Glide.get(this).clearMemory();
        Glide.get(this).trimMemory(TRIM_MEMORY_COMPLETE);
        onTrimMemory(ComponentCallbacks2.TRIM_MEMORY_MODERATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
        Glide.get(this).trimMemory(TRIM_MEMORY_COMPLETE);
        onTrimMemory(ComponentCallbacks2.TRIM_MEMORY_MODERATE);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(this).clearMemory();
        Glide.get(this).trimMemory(TRIM_MEMORY_COMPLETE);
    }
}
