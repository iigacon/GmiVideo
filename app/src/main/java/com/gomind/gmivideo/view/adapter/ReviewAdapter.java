package com.gomind.gmivideo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gomind.data.entities.Review;
import com.gomind.gmivideo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder>{
    private List<Review> reviews;
    private Context context;
    public ReviewAdapter(List<Review> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindCard(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.content)
        TextView content;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bindCard(Review review){
            author.setText(review.getAuthor());
            content.setText(review.getContent());
        }
    }
}
