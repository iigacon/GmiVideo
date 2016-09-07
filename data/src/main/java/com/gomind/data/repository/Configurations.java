package com.gomind.data.repository;

import rx.Observable;

/**
 * Created by Duc on 9/5/16.
 */
public interface Configurations {
    Observable<com.gomind.data.entities.Configuration> getConfiguration();
}
