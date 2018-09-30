/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket;

import android.support.annotation.Nullable;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.Model.ChatModel;
import com.example.solid.mindgame.ui.sebseGhatar.Model.InitData;
import com.example.solid.mindgame.ui.sebseGhatar.Model.MoveData;
import com.example.solid.mindgame.ui.sebseGhatar.Model.SocketResponse;
import com.example.solid.mindgame.ui.sebseGhatar.Model.StateModel;
import com.google.gson.Gson;

import okhttp3.Response;
import okio.ByteString;

public class WebSocketCall extends okhttp3.WebSocketListener {
    private WebSocketListener mListener;
    private Gson gson;

    public interface WebSocketListener {
        void newMove(int row, int col, int layer, boolean player);

        void yourTurn(boolean turn);

        void lastTurn(boolean lastTurn);

        void newMessage(String messageText, boolean userMessage);

        void gameIsStarted();

        void userDisconnected(boolean user);
    }

    public WebSocketCall(WebSocketListener mListener) {
        this.mListener = mListener;
        this.gson = new Gson();
    }

    @Override
    public void onOpen(okhttp3.WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(okhttp3.WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        SocketResponse socketResponse = gson.fromJson(text, SocketResponse.class);
        switch (socketResponse.getType()) {
            case PersistenceContract.GameMethod.INIT:
                InitData init = gson.fromJson(socketResponse.getData(), InitData.class);
                mListener.yourTurn(init.UserTurn());
                break;
            case PersistenceContract.GameMethod.MOVE:
                MoveData move = gson.fromJson(socketResponse.getData(), MoveData.class);
                mListener.lastTurn(move.isPlayer());
                mListener.newMove(move.getRow(), move.getCol(), move.getLayer(), move.isPlayer());
                break;
            case PersistenceContract.GameMethod.MESSAGE:
                ChatModel chat = gson.fromJson(socketResponse.getData(), ChatModel.class);
                mListener.newMessage(chat.getMessageText(), chat.isUserMessage());
                break;
            case PersistenceContract.GameMethod.STATE:
                StateModel state = gson.fromJson(socketResponse.getData(), StateModel.class);
                if (state.getState().equals(PersistenceContract.GameMethod.STATE_DISCONNECTED)) {
                    mListener.userDisconnected(state.isUser());
                }
                mListener.gameIsStarted();
                break;
        }
    }

    @Override
    public void onMessage(okhttp3.WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onClosing(okhttp3.WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        this.mListener = null;
    }

    @Override
    public void onClosed(okhttp3.WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        this.mListener = null;
    }

    @Override
    public void onFailure(okhttp3.WebSocket webSocket, Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        this.mListener = null;
    }
}
