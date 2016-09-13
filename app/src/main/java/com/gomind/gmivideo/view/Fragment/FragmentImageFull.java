package com.gomind.gmivideo.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.activity.Image;
import com.gomind.gmivideo.view.ulti.MessageImageFull;
import com.gomind.gmivideo.view.ulti.MessageImages;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
//TODO
public class FragmentImageFull extends Fragment {
    private List<Image> images;
    private int currentPosition;
    @Subscribe
    public void onEvent(MessageImages messageImages){
        this.images=messageImages.getImages();
    }
    @Subscribe
    public void onEvent(MessageImageFull messageImageFull){
        this.currentPosition=messageImageFull.getPosition();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image_full,container,false);
        EventBus.getDefault().register(this);
        return view;
    }
}
