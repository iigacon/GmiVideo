package com.gomind.gmivideo.vmp.presenter;

import com.gomind.gmivideo.vmp.view.View;

/**
 * Created by Duc on 8/30/16.
 */
public interface Presenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView (View v);

    void onCreate();

    void loadMore();
}