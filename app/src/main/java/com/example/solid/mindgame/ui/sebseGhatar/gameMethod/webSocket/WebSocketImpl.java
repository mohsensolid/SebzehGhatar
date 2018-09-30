/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket;


import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WebSocketImpl implements WebSocketContract {
    private static final int CLOSE_SOCKET = 1000;

    private OkHttpClient client;
    private Request request;
    private WebSocket ws;
    @Inject
    public WebSocketImpl(OkHttpClient client, Request request ) {
        this.client = client;
        this.request = request;
    }

    @Override
    public void send(String msg) {
        ws.send(msg);
    }

    @Override
    public void start(WebSocketCall.WebSocketListener listener) {
        WebSocketCall webSocketCall = new WebSocketCall(listener);
        ws = client.newWebSocket(request, webSocketCall);
    }

    @Override
    public void disconnect() {
        if (ws != null) {
            ws.close(CLOSE_SOCKET, null);
            ws = null;
        }
           client.dispatcher().executorService().shutdown();
    }
}
