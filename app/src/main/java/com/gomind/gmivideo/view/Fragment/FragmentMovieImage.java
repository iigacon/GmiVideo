package com.gomind.gmivideo.view.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.gomind.gmivideo.R;


public class FragmentMovieImage extends Fragment {
    public FragmentMovieImage() {
    }

    private String image_link;
    public FragmentMovieImage instance(String image_link) {
        FragmentMovieImage fragment=new FragmentMovieImage();
        Bundle args = new Bundle();
        args.putString("image_link", image_link);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie_image,container,false);
        ImageView imageView= (ImageView) view.findViewById(R.id.imageView);
        image_link=getArguments().getString("image_link");
        Glide.with(this)
                .load(image_link)
                .crossFade()
                .into(imageView);
        return view;
    }
}
