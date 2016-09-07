package com.gomind.gmivideo.Injector.Module;

import com.gomind.data.repository.ConfigurationRepository;
import com.gomind.domain.GetConfiguration;
import com.gomind.gmivideo.Injector.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class ConfigurationModule {
    @Provides @Activity
    public GetConfiguration provideGetConfiguration(ConfigurationRepository configurationRepository,
                                                    @Named("ui_thread")Scheduler uiThread,
                                                    @Named("executor_thread")Scheduler executorThread){
        return new GetConfiguration(configurationRepository, uiThread, executorThread);
    }
}
