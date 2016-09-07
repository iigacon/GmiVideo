package com.gomind.domain;

import com.gomind.data.entities.Configuration;
import com.gomind.data.repository.ConfigurationRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Duc on 9/5/16.
 */
public class GetConfiguration extends UseCase<Configuration>{
    private ConfigurationRepository configurationRepository;
    private Scheduler uiThread;
    private Scheduler executorThread;
    @Inject
    public GetConfiguration(ConfigurationRepository configurationRepository, Scheduler uiThread, Scheduler executorThread) {
        this.configurationRepository = configurationRepository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable<Configuration> buildObserable() {
        return configurationRepository.getConfiguration()
                .observeOn(uiThread)
                .subscribeOn(executorThread);
    }
}
