package com.example.solid.mindgame.internaldi.modules;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Solid on 3/18/2017.
 */

@Module
public class NetModule {
    private String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        Dispatcher dispatcher = new Dispatcher();
        client.dispatcher(dispatcher);
        return client.build();
    }

    @Provides
    @Singleton
    Request request() {
        return new Request.Builder()
                .url(mBaseUrl).build();
    }

}
