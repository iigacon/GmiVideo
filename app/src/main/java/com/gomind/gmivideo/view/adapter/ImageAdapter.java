package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gomind.data.entities.Image;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.vmp.ulti.RecyclerImageClickListiner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Duc on 9/7/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>{
    private List<Image> images;
    private Context context;
    private RecyclerImageClickListiner imageClickListiner;
    public ImageAdapter(List<Image> images, Context context, RecyclerImageClickListiner imageClickListiner) {
        this.images = images;
        this.context = context;
        this.imageClickListiner = imageClickListiner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.image_item, parent);
        return new MyViewHolder(view,imageClickListiner);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindImage(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
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
        public void bindImage(Image image){
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w500"+image.getFile_path())
                    .placeholder(R.drawable.header)
                    .error(R.drawable.header)
                    .into(imageView);
            imageView.setOnClickListener(v->imageClickListiner.OnElementClick(image.getFile_path()));
        }
    }
}
