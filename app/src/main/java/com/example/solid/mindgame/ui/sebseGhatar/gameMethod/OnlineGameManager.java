/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod;


import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.Model.ChatModel;
import com.example.solid.mindgame.ui.sebseGhatar.Model.MoveData;
import com.example.solid.mindgame.ui.sebseGhatar.Model.SocketRequest;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketCall;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketContract;
import com.google.gson.Gson;

import javax.inject.Inject;

public class OnlineGameManager implements GameManager, WebSocketCall.WebSocketListener {
    private boolean isGameStarted = false;
    private boolean yourColor;
    private boolean lastTurn;
    private GameListener mListener;
    private Gson gson;
    private WebSocketContract webSocketContract;

    @Inject
    public OnlineGameManager(Gson gson,WebSocketContract webSocketContract) {
        this.gson = gson;
        this.webSocketContract = webSocketContract;
    }

    @Override
    public boolean isYourTurn() {
        return yourColor != lastTurn && isGameStarted;
    }

    @Override
    public void cellClickedAt(int row, int col, int layer) {
        MoveData move = new MoveData(row, col, layer, yourColor);
        SocketRequest request = new SocketRequest(PersistenceContract.GameMethod.MOVE, gson.toJson(move));
        this.webSocketContract.send(gson.toJson(request));
    }


    @Override
    public void dispose() {
        this.mListener = null;
        this.webSocketContract.disconnect();
    }

    @Override
    public void sendMessage(String msg) {
        ChatModel chatModel = new ChatModel(msg, true);
        SocketRequest request = new SocketRequest(PersistenceContract.GameMethod.MESSAGE, gson.toJson(chatModel));
        this.webSocketContract.send(gson.toJson(request));
    }


    @Override
    public void start(GameListener gameListener) {
        this.mListener = gameListener;
        this.webSocketContract.start(this);
    }

    @Override
    public void newMove(int row, int col, int layer, boolean player) {
        mListener.changeItemAt(row, col, layer, player);
    }

    @Override
    public void yourTurn(boolean turn) {
        this.yourColor = turn;
    }

    @Override
    public void lastTurn(boolean lastTurn) {
        this.lastTurn = lastTurn;
    }

    @Override
    public void newMessage(String messageText, boolean userMessage) {
        mListener.newMessageRecive(messageText, userMessage == yourColor);
    }

    @Override
    public void gameIsStarted() {
        this.isGameStarted = true;
        mListener.opponentFind();
    }

    @Override
    public void userDisconnected(boolean user) {
        mListener.userDisconnected(user == yourColor);
    }

}
