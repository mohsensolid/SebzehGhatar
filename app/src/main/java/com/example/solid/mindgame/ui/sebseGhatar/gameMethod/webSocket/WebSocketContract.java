/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket;

public interface WebSocketContract {
    void send(String msg);
    void start(WebSocketCall.WebSocketListener listener);
    void disconnect();
}
