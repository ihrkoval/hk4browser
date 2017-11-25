package com.hk4browser.app.hk4browser.system;

import android.app.Application;

/**
 * Created by anton on 05.11.2017.
 */

public class CustomApp extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent
                .builder()
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}