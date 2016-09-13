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
import android.widget.Toast;

import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.adapter.ImageAdapter;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageShowActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_ImageShow)
    Toolbar toolbar;
    @BindView(R.id.grid_image)
    RecyclerView grid_image;
    private List<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        images=Parcels.unwrap(getIntent().getParcelableExtra("list.image"));
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        GridLayoutManager glayout=new GridLayoutManager(this, 3);
        if(images!=null) {
            ImageAdapter imageAdapter = new ImageAdapter(images, this, image_path -> {
                //TODO
                Toast.makeText(ImageShowActivity.this, image_path, Toast.LENGTH_SHORT).show();
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
    public static void start(Context context, List<Image> images){
        Intent intent=new Intent(context,ImageShowActivity.class);
        intent.putExtra("list.image", Parcels.wrap(images));
        context.startActivity(intent);
    }

}
