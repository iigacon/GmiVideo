package com.gomind.gmivideo.view.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gomind.data.entities.Movie;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.activity.MovieDetailActivity;
import com.gomind.gmivideo.view.adapter.MovieCatelogyAdapter;
import com.gomind.gmivideo.vmp.presenter.Presenter;
import com.gomind.gmivideo.vmp.view.MovieBaseView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class FragmentMovie extends Fragment implements MovieBaseView {
    private MovieCatelogyAdapter movieAdapter;
    protected String idMovie;
    @BindView(R.id.recycler_movie_catelogy)
    RecyclerView recyclerView;
    protected Presenter presenter;
    private boolean enableScroll=false;
    protected View viewer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_catelogy, container, false);
        ButterKnife.bind(this, view);
        viewer=view;
        initilizeInjector();
        presenter.attachView(this);
        presenter.onCreate();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFitsSystemWindows(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(mOnScrollListener);
        return view;
    }

    public abstract void initilizeInjector();

    @Override
    public void bindMovieBase(List<Movie> movies) {
        movieAdapter = new MovieCatelogyAdapter(movies, getActivity(), (position, idMovie) -> {
            if (idMovie != null) {
                MovieDetailActivity.start(getActivity(), idMovie);
            }
        },(id, imageView) -> {

        });
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
        enableScroll=true;
    }

    @Override
    public void bindLoadMore(int count) {
        movieAdapter.notifyItemRangeInserted(movieAdapter.getItemCount() + count, count);
        if(count>0){
            enableScroll=true;
        }else enableScroll=false;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            int visiableItemsCount = layoutManager.getChildCount();
            int totalItemsCount = layoutManager.getItemCount();
            int firstVisiableItemPos = layoutManager.findFirstVisibleItemPosition();
            if ((visiableItemsCount + firstVisiableItemPos + 16) >= totalItemsCount & enableScroll) {
                presenter.loadMore();
            }
        }
    };
}
