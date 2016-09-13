package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.PersonMovieCredit;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.vmp.ulti.OnClickWithId;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Duc on 9/7/16.
 */
public class MovieCastPersonAdapter extends RecyclerView.Adapter<MovieCastPersonAdapter.MyViewHolder>{
    private List<PersonMovieCredit> movieCredits;
    private Context context;
    private OnClickWithId onClickWithId;
    public MovieCastPersonAdapter(List<PersonMovieCredit> movieCredits, Context context,OnClickWithId onClickWithId){
        this.movieCredits = movieCredits;
        this.context = context;
        this.onClickWithId = onClickWithId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_similar_item,parent,false);
        return new MyViewHolder(view, onClickWithId);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindCrewCard(movieCredits.get(position));
    }

    @Override
    public int getItemCount() {
        return movieCredits.size();
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
       public MyViewHolder(View itemView, OnClickWithId onClickWithId) {
           super(itemView);
           ButterKnife.bind(this,itemView);
           title.setOnClickListener(v->onClickWithId.onElementClick(movieCredits.get(getPosition()).getId()));
           poster.setOnClickListener(v->onClickWithId.onElementClick(movieCredits.get(getPosition()).getId()));
       }
        public void bindCrewCard(PersonMovieCredit personMovieCredit){
            title.setText(personMovieCredit.getTitle());
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w300"+personMovieCredit.getPoster_path())
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(poster);
        }
   }
}
