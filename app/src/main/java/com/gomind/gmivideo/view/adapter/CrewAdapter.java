package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.Crew;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.vmp.ulti.RecyclerCrewClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Duc on 9/7/16.
 */
public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.MyViewHolder>{
    private List<Crew> crews;
    private Context context;
    private RecyclerCrewClickListener crewClickListener;
    public CrewAdapter(List<Crew> crews, Context context, RecyclerCrewClickListener crewClickListener){
        this.crews=crews;
        this.context = context;
        this.crewClickListener = crewClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_crew_item,parent,false);
        return new MyViewHolder(view,crewClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindCrewCard(crews.get(position));
    }

    @Override
    public int getItemCount() {
        return crews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.thumbnail_crew)
        ImageView thumbnail;
        @BindView(R.id.title_crew)
        TextView title;
       public MyViewHolder(View itemView, RecyclerCrewClickListener crewClickListener) {
           super(itemView);
           ButterKnife.bind(this,itemView);
           bindClick(itemView,crewClickListener);
       }
        public void bindCrewCard(Crew crew){
            title.setText(crew.getName()+"\n("+crew.getJob()+")");
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w300"+crew.getProfile_path())
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(thumbnail);
        }
        public void bindClick(View itemView, RecyclerCrewClickListener crewClickListener){
            itemView.setOnClickListener(v -> crewClickListener.onElementClick(crews.get(getPosition()).getId()));
            thumbnail.setOnClickListener(v -> crewClickListener.onElementClick(crews.get(getPosition()).getId()));
        }
   }
}
