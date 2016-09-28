package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.Movie;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.ulti.WatchlistMovie;
import com.gomind.gmivideo.vmp.ulti.OnClickWithWatchList;
import com.gomind.gmivideo.vmp.ulti.RecyclerMovieCatelogyClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieCatelogyAdapter extends RecyclerView.Adapter<MovieCatelogyAdapter.MyViewHolder>{
    private List<Movie> movies;
    private Context context;
    private RecyclerMovieCatelogyClickListener movieCatelogyClickListener;
    private OnClickWithWatchList onClickWithWatchList;
    public MovieCatelogyAdapter(List<Movie> Movies, Context context, RecyclerMovieCatelogyClickListener movieCatelogyClickListener, OnClickWithWatchList onClickWithWatchList){
        this.movies = Movies;
        this.context = context;
        this.movieCatelogyClickListener = movieCatelogyClickListener;
        this.onClickWithWatchList=onClickWithWatchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_catelogy_item,parent,false);
        return new MyViewHolder(view,movieCatelogyClickListener, onClickWithWatchList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindCastCard(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.movie_poster)
        ImageView poster;
        @BindView(R.id.add_favorite)
        ImageView add_favorite;

       public MyViewHolder(View itemView, RecyclerMovieCatelogyClickListener movieCatelogyClickListener, OnClickWithWatchList onClickWithWatchList) {
           super(itemView);
           ButterKnife.bind(this,itemView);
           DisplayMetrics displaymetrics = new DisplayMetrics();
           ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
           int width = displaymetrics.widthPixels;
           poster.getLayoutParams().height=(int)(width/2*1.4);
           poster.setOnClickListener(v->movieCatelogyClickListener.onElementClick(getPosition(),movies.get(getPosition()).getId()));
//           title.setOnClickListener(v->movieCatelogyClickListener.onElementClick(getPosition(),movies.get(getPosition()).getPoster_path()));
           add_favorite.setOnClickListener(v->{
               onClickWithWatchList.onElementClick(movies.get(getPosition()).getId(), add_favorite);
               if(!WatchlistMovie.check(movies.get(getPosition()).getId())){
                   add_favorite.setImageResource(R.drawable.bookmark_check);
               }
               else{
                   add_favorite.setImageResource(R.drawable.attach_favorite);
               }
           });
       }
        public void bindCastCard(Movie movie){
//            title.setText(movie.getTitle());
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w185"+movie.getPoster_path())
                    .error(R.drawable.header)
                    .into(poster);
            if(WatchlistMovie.check(movie.getId())){
                add_favorite.setImageResource(R.drawable.bookmark_check);
            }else add_favorite.setImageResource(R.drawable.attach_favorite);
        }

   }

}
