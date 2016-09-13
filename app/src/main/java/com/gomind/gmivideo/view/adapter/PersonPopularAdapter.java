package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.PersonPopular;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.vmp.ulti.OnClickWithId;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonPopularAdapter extends RecyclerView.Adapter<PersonPopularAdapter.MyViewHolder>{
    private List<PersonPopular> personPopulars;
    private Context context;
    private OnClickWithId onClickWithId;
    public PersonPopularAdapter(List<PersonPopular> personPopulars, Context context, OnClickWithId onClickWithId){
        this.personPopulars = personPopulars;
        this.context = context;
        this.onClickWithId = onClickWithId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.person_popular_item,parent,false);
        return new MyViewHolder(view,onClickWithId);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindCrewCard(personPopulars.get(position));
    }

    @Override
    public int getItemCount() {
        return personPopulars.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_profile)
        ImageView profile;
        @BindView(R.id.name)
        TextView name;

       public MyViewHolder(View itemView, OnClickWithId onClickWithId) {
           super(itemView);
           ButterKnife.bind(this,itemView);
           DisplayMetrics displaymetrics = new DisplayMetrics();
           ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
           int width = displaymetrics.widthPixels;
           profile.getLayoutParams().height=(int)(width/3*1.4);
           name.setOnClickListener(v->onClickWithId.onElementClick(personPopulars.get(getPosition()).getId()));
           profile.setOnClickListener(v->onClickWithId.onElementClick(personPopulars.get(getPosition()).getId()));
       }
        public void bindCrewCard(PersonPopular personPopular){
            name.setText(personPopular.getName());
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w300"+personPopular.getProfile_path())
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(profile);
        }
   }
}
