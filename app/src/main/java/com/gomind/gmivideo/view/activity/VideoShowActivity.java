package com.gomind.gmivideo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.adapter.VideoAdapter;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoShowActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_ImageShow)
    Toolbar toolbar;
    @BindView(R.id.grid_image)
    RecyclerView grid_image;
    private List<Video> videos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        videos=Parcels.unwrap(getIntent().getParcelableExtra("list.image"));
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        GridLayoutManager glayout=new GridLayoutManager(this, 3);
        if(videos!=null) {
            VideoAdapter imageAdapter = new VideoAdapter(videos, this, image_path -> {
                Intent intent=new Intent(this,PlayerViewDemoActivity.class);
                intent.putExtra("idYoutube",image_path);
                startActivity(intent);
            });
            grid_image.setAdapter(imageAdapter);
        }
        grid_image.setLayoutManager(glayout);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return false;
            }
            default: return false;
        }
    }
    public static void start(Context context, List<Video> videos){
        Intent intent=new Intent(context,VideoShowActivity.class);
        intent.putExtra("list.image", Parcels.wrap(videos));
        context.startActivity(intent);
    }

}
