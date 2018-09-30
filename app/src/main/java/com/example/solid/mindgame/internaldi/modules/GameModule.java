package com.example.solid.mindgame.internaldi.modules;

import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DefaultScore;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DiagonalScore;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.LocalGameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.OnlineGameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketContract;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketImpl;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Module(includes = NetModule.class)
public class GameModule {

    @Provides
    @Singleton
    LocalGameManager localGameManager() {
        return new LocalGameManager();
    }

    @Provides
    @Singleton
    DefaultScore defaultScore() {
        return new DefaultScore();
    }

    @Provides
    @Singleton
    DiagonalScore diagonalScore() {
        return new DiagonalScore();
    }


    @Provides
    @Singleton
    WebSocketContract webSocketContract(OkHttpClient okHttpClient, Request request) {
        return new WebSocketImpl(okHttpClient, request);
    }

    @Provides
    @Singleton
    OnlineGameManager onlineGameManager(Gson gson, WebSocketContract webSocketContract) {
        return new OnlineGameManager(gson, webSocketContract);
    }

}
