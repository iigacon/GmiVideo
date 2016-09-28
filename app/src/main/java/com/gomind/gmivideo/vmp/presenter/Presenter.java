package com.gomind.gmivideo.vmp.presenter;

import com.gomind.gmivideo.vmp.view.View;


public interface Presenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView (View v);

    void onCreate();

    void loadMore();
}