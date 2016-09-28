package com.gomind.gmivideo.view.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gomind.data.entities.Credits;
import com.gomind.data.entities.Image;
import com.gomind.data.entities.Movie;
import com.gomind.data.entities.MovieDetail;
import com.gomind.data.entities.MovieSimilar;
import com.gomind.data.entities.PostWatchList;
import com.gomind.data.entities.Video;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerMovieGenreComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.MovieGenreModule;
import com.gomind.gmivideo.Injector.Module.PostWatchListModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.view.adapter.MovieCatelogyAdapter;
import com.gomind.gmivideo.view.ulti.WatchlistMovie;
import com.gomind.gmivideo.vmp.presenter.MovieGenrePresenter;
import com.gomind.gmivideo.vmp.presenter.PostWatchListPresenter;
import com.gomind.gmivideo.vmp.ulti.AccountStatus;
import com.gomind.gmivideo.vmp.view.MovieDetailView;
import com.gomind.gmivideo.vmp.view.MovieGenreView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentMovieCatalog extends Fragment implements MovieGenreView, MovieDetailView {
    private MovieCatelogyAdapter movieCatelogyAdapter;
    @BindView(R.id.recycler_movie_catelogy)
    RecyclerView recyclerView;
    @Inject
    MovieGenrePresenter movieGenrePresenter;

    @Inject
    PostWatchListPresenter postWatchListPresenter;


    public static FragmentMovieCatalog newInstance(String id_catelogy) {
        FragmentMovieCatalog fragment = new FragmentMovieCatalog();
        Bundle args = new Bundle();
        args.putString("id", id_catelogy);
        fragment.setArguments(args);
        return fragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_catelogy, container, false);
        ButterKnife.bind(this,view);
        String idMovie =getArguments().getString("id");
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerMovieGenreComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .movieGenreModule(new MovieGenreModule(idMovie))
                .postWatchListModule(new PostWatchListModule())
                .build().inject(this);
        movieGenrePresenter.attachView(this);
        movieGenrePresenter.onCreate();
        postWatchListPresenter.attachView(this);
        postWatchListPresenter.setId(AccountStatus.user_id);
        postWatchListPresenter.setSession_id(AccountStatus.session_id);
        return view;
    }
    private boolean currentFavorite;
    private String currentIdMovie;
    @Override
    public void bindMovieBase(List<Movie> movies) {
        movieCatelogyAdapter = new MovieCatelogyAdapter(movies, getActivity(), (position, idMovie) -> {
            if(idMovie!=null){
                MovieDetailActivity.start(getActivity(),idMovie);
            }
        }, (id, imageView) -> {
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
        recyclerView.setAdapter(movieCatelogyAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFitsSystemWindows(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    public void bindLoadMore(int count) {
        movieCatelogyAdapter.notifyItemRangeInserted(movieCatelogyAdapter.getItemCount()+count,count);
    }


    private RecyclerView.OnScrollListener mOnScrollListener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            GridLayoutManager layoutManager= (GridLayoutManager) recyclerView.getLayoutManager();
            int visiableItemsCount=layoutManager.getChildCount();
            int totalItemsCount=layoutManager.getItemCount();
            int firstVisiableItemPos=layoutManager.findFirstVisibleItemPosition();
            if((visiableItemsCount+firstVisiableItemPos+16)>=totalItemsCount){
                movieGenrePresenter.loadMore();
            }
        }
    };

    @Override
    public void bindMovieDetail(MovieDetail movieDetail) {

    }

    @Override
    public void bindMovieSimilar(List<MovieSimilar> movieSimilars) {

    }

    @Override
    public void bindMovieImage(List<Image> images) {

    }

    @Override
    public void playYoutubeTrailer(List<Video> videoTrailer) {

    }

    @Override
    public void bindCreditMovie(Credits credits) {

    }

    @Override
    public void videoMovie(String idMovie) {

    }

    @Override
    public void imageMovie(String idMovie) {

    }

    @Override
    public void bindPostWatchList(PostWatchList postWatchList) {
        if(postWatchList.getStatus_code()==13){
            WatchlistMovie.remove(currentIdMovie);
            Toast.makeText(getActivity(), "Watchlist removed!", Toast.LENGTH_SHORT).show();
        }
        if(postWatchList.getStatus_code()==1){
            WatchlistMovie.add(currentIdMovie);
            Toast.makeText(getActivity(), "Watchlist added!", Toast.LENGTH_SHORT).show();
        }



    }
}
