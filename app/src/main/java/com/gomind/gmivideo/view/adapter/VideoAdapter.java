package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.activity.Video;
import com.gomind.gmivideo.vmp.ulti.RecyclerImageClickListiner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder>{
    private List<Video> videos;
    private Context context;
    private RecyclerImageClickListiner imageClickListiner;
    public VideoAdapter(List<Video> videos, Context context, RecyclerImageClickListiner imageClickListiner) {
        this.videos = videos;
        this.context = context;
        this.imageClickListiner = imageClickListiner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_item, parent,false);
        return new MyViewHolder(view,imageClickListiner);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindVideo(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        RecyclerImageClickListiner imageClickListiner;
        public MyViewHolder(View itemView, RecyclerImageClickListiner imageClickListiner) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.imageClickListiner=imageClickListiner;
        }
        public void bindVideo(Video video){
            Glide.with(context)
                    .load("http://img.youtube.com/vi/"+video.getKey()+"/mqdefault.jpg")
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(imageView);
            imageView.setOnClickListener(v->imageClickListiner.OnElementClick(video.getKey()));
        }
    }
}
