/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame;


import android.app.Application;

import com.example.solid.mindgame.internaldi.component.ApplicationComponent;
import com.example.solid.mindgame.internaldi.component.DaggerApplicationComponent;
import com.example.solid.mindgame.internaldi.modules.ApplicationModule;
import com.example.solid.mindgame.internaldi.modules.NetModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;



public class App extends Application
{
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeCalligraphy();
    }
    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .setDefaultFontPath("IRAN_Sans_Bold.ttf")
                .build()
        );
    }


    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
//                .netModule(new NetModule("ws://10.0.2.2:8000/ws/game/random/"))
                .netModule(new NetModule("wss://morning-crag-78586.herokuapp.com/ws/game/random/"))
                .build();

    }
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
