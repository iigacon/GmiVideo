package com.gomind.gmivideo.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.activity.Image;
import com.gomind.gmivideo.view.adapter.ImageAdapter;
import com.gomind.gmivideo.view.ulti.MessageImages;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//TODO
public class FragmentGridImage extends Fragment{
    @BindView(R.id.toolbar_ImageShow)
    Toolbar toolbar;
    @BindView(R.id.grid_image)
    RecyclerView grid_image;
    private List<Image> images;
    @Subscribe
    public void onEvent(MessageImages messageImages){
        this.images=messageImages.getImages();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image_show,container,false);
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        GridLayoutManager glayout=new GridLayoutManager(getActivity(), 3);
        if(images!=null) {
            ImageAdapter imageAdapter = new ImageAdapter(images, getActivity(), image_path -> {
                //TODO
                Toast.makeText(getActivity(), image_path, Toast.LENGTH_SHORT).show();
            });
            grid_image.setAdapter(imageAdapter);
        }
        grid_image.setLayoutManager(glayout);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.detail_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                getActivity().onBackPressed();
                return false;
            }
            default: return false;
        }
    }
}
