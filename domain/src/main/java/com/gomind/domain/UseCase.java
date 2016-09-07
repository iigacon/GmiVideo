package com.gomind.domain;


import rx.Observable;


public abstract class UseCase<T> {
   public abstract Observable<T> buildObserable();
   public Observable<T> execute(){
      return buildObserable();
   }
}
