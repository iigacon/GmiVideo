package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.Movie;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.vmp.ulti.RecyclerClickListener;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.MoviePopularViewHolder>{
    private List<Movie> movies;
    private Context context;
    private RecyclerClickListener recyclerClickListener;
    public MoviePopularAdapter(List<Movie> movies, Context context, RecyclerClickListener recyclerClickListener) {
        this.movies = movies;
        this.context = context;
        this.recyclerClickListener = recyclerClickListener;
    }

    @Override
    public MoviePopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView= LayoutInflater.from(context).inflate(R.layout.movie_adapter, parent, false);
        return new MoviePopularViewHolder(rootView, recyclerClickListener);
    }

    @Override
    public void onBindViewHolder(MoviePopularViewHolder holder, int position) {
        holder.binMoviePopular(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviePopularViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_avenger_placeholder_name)   TextView avengerPlaceholderTitleTextView;
        @BindColor(R.color.brand_primary)           int mColorPrimary;
        @BindView(R.id.item_avenger_title)
        TextView avengerTitleTextView;
        @BindView(R.id.item_avenger_thumb)
        ImageView avengerThumbImageView;

        public MoviePopularViewHolder(View itemView, final RecyclerClickListener recyclerClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, recyclerClickListener);
        }
        public void binMoviePopular(Movie movie){
            avengerTitleTextView.setText(movie.getOriginal_title());
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w500"+movie.getBackdrop_path())
                    .crossFade()
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(avengerThumbImageView);
        }
        private void bindListener(View itemView, final RecyclerClickListener recyclerClickListener) {
            itemView.setOnClickListener(v ->
                    recyclerClickListener.onElementClick(getPosition(), avengerTitleTextView, avengerThumbImageView));
        }
    }
}
