package com.gomind.gmivideo.Injector.Module;

import android.content.Context;

import com.gomind.gmivideo.Injector.Activity;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private final Context mContext;

    public ActivityModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides @Activity
    Context provideActivityContext() {
        return mContext;
    }
}
