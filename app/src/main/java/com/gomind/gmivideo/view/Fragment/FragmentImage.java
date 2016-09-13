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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Duc on 9/12/16.
 */
public class FragmentImage extends Fragment {
    @BindView(R.id.imageView)
    ImageView image;
    public FragmentImage newInstance(String link) {
        FragmentImage fragment=new FragmentImage();
        Bundle arg=new Bundle();
        arg.putString("image.link", link);
        fragment.setArguments(arg);
        return fragment;
    }
    String link;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image_slideshow, container, false);
        ButterKnife.bind(this,view);
        try {
            link = getArguments().getString("image.link");
        }catch (Exception e){
            e.printStackTrace();
        }
        Glide.with(getActivity())
                .load(link)
                .error(R.drawable.header)
                .into(image);
        return view;
    }
}
