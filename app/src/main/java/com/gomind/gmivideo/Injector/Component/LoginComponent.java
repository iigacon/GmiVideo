package com.gomind.gmivideo.Injector.Component;

import com.gomind.gmivideo.Injector.Module.AccountModule;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.GetSessionModule;
import com.gomind.gmivideo.Injector.Module.LoginModule;
import com.gomind.gmivideo.Injector.Module.RequestTokenModule;
import com.gomind.gmivideo.Injector.Module.WatchListMovieModule;
import com.gomind.gmivideo.Injector.Scope.Activity;
import com.gomind.gmivideo.view.activity.LoginActivity;

import dagger.Component;


@Activity
@Component(dependencies = AppComponent.class, modules = {AccountModule.class, WatchListMovieModule.class, ActivityModule.class, RequestTokenModule.class, LoginModule.class, GetSessionModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
