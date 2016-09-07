package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.MovieSimilar;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.vmp.ulti.RecyclerMovieSimilarClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Duc on 9/7/16.
 */
public class MovieSimilarAdapter extends RecyclerView.Adapter<MovieSimilarAdapter.MyViewHolder>{
    private List<MovieSimilar> movieSimilars;
    private Context context;
    private RecyclerMovieSimilarClickListener movieSimilarClickListener;
    public MovieSimilarAdapter(List<MovieSimilar> movieSimilars, Context context, RecyclerMovieSimilarClickListener movieSimilarClickListener){
        this.movieSimilars = movieSimilars;
        this.context = context;
        this.movieSimilarClickListener = movieSimilarClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_similar_item,parent,false);
        return new MyViewHolder(view,movieSimilarClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindCrewCard(movieSimilars.get(position));
    }

    @Override
    public int getItemCount() {
        return movieSimilars.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.movie_similar_poster)
        ImageView poster;
        @BindView(R.id.movie_similar_title)
        TextView title;
        @BindView(R.id.similar_add_favorite)
        ImageView add_favorite;
        @OnClick(R.id.similar_add_favorite)
        void addFavorite(){
            //TODO
        }
       public MyViewHolder(View itemView, RecyclerMovieSimilarClickListener movieSimilarClickListener) {
           super(itemView);
           ButterKnife.bind(this,itemView);
//           bindClick(itemView,movieSimilarClickListener);
           title.setOnClickListener(v->movieSimilarClickListener.onElementClick(getPosition(),movieSimilars.get(getPosition()).getId()));
           poster.setOnClickListener(v->movieSimilarClickListener.onElementClick(getPosition(),movieSimilars.get(getPosition()).getId()));
       }
        public void bindCrewCard(MovieSimilar movieSimilar){
            title.setText(movieSimilar.getTitle());
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w500"+movieSimilar.getPoster_path())
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(poster);
        }
        public void bindClick(View itemView, RecyclerMovieSimilarClickListener movieSimilarClickListener){
            itemView.setOnClickListener(v -> movieSimilarClickListener.onElementClick(getPosition(),movieSimilars.get(getPosition()).getId()));
        }
   }
}
