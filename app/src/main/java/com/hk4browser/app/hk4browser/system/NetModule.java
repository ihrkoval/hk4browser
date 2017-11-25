package com.hk4browser.app.hk4browser.system;


import com.hk4browser.app.hk4browser.http.Http;
import com.hk4browser.app.hk4browser.http.IHttp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anton on 30.10.2017.
 */

@Module
public class NetModule {

    @Singleton
    @Provides
    public IHttp getHttp(){
        return new Http();
    }
}