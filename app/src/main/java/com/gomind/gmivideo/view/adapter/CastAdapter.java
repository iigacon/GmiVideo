package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.Cast;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.vmp.ulti.RecyclerCastClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Duc on 9/7/16.
 */
public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder>{
    private List<Cast> casts;
    private Context context;
    private RecyclerCastClickListener castClickListener;
    public CastAdapter(List<Cast> casts, Context context, RecyclerCastClickListener castClickListener){
        this.casts=casts;
        this.context = context;
        this.castClickListener = castClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_cast_item,parent,false);
        return new MyViewHolder(view,castClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindCastCard(casts.get(position));
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.title_cast)
        TextView title;
        @BindView(R.id.card_view)
        CardView cardView;
       public MyViewHolder(View itemView, RecyclerCastClickListener recyclerCastClickListener) {
           super(itemView);
           ButterKnife.bind(this,itemView);
//           bindClick(itemView,recyclerCastClickListener);
           thumbnail.setOnClickListener(v->recyclerCastClickListener.onElementClick(casts.get(getPosition()).getId()));
           title.setOnClickListener(v->recyclerCastClickListener.onElementClick(casts.get(getPosition()).getId()));
       }
        public void bindCastCard(Cast cast){
            title.setText(cast.getName()+"\n("+cast.getCharacter()+")");
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w300"+cast.getProfile_path())
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(thumbnail);
        }

   }
}
