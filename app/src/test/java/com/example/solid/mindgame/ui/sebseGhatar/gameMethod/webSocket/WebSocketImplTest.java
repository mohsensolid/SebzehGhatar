/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket;

import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.OnlineGameManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WebSocketImplTest {

    @Mock
    private OnlineGameManager onlineGameManager;
    private OkHttpClient mClient;
    private WebSocketImpl socketImpl;

    private OkHttpClient getClient() {
        return new OkHttpClient();
    }

    private Request getRequest() {
        return new Request.Builder().url("ws://10.0.2.2:8000/ws/game/random/").build();
    }

    @Before
    public void setUp() throws Exception {
        this.mClient = getClient();
        socketImpl = new WebSocketImpl(mClient, getRequest());
    }

    @Test
    public void start() throws Exception {
        socketImpl.start(onlineGameManager);
        Assert.assertTrue(mClient.dispatcher().runningCallsCount() == 1);
        Assert.assertTrue(getWebSocket() != null);
    }

    @Test
    public void disconnect() throws Exception {
        socketImpl.disconnect();
        Assert.assertTrue(mClient.dispatcher().runningCallsCount() == 0);
        Assert.assertTrue(getWebSocket() == null);
    }

    private WebSocket getWebSocket() throws Exception {
        Field fieldlastTurn = socketImpl.getClass().getDeclaredField("ws");
        fieldlastTurn.setAccessible(true);
        return (WebSocket) fieldlastTurn.get(socketImpl);
    }

}