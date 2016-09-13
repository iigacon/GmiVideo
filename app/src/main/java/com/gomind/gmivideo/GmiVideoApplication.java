package com.gomind.gmivideo;

import android.app.Application;

import com.gomind.gmivideo.Injector.Component.AppComponent;
import com.gomind.gmivideo.Injector.Component.DaggerAppComponent;
import com.gomind.gmivideo.Injector.Module.AppModule;



public class GmiVideoApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
