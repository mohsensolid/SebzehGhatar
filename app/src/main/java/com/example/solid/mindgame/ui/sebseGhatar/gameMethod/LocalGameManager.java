/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod;

import com.example.solid.mindgame.Utils.PersistenceContract.SebseGhatar;

import javax.inject.Inject;

public class LocalGameManager implements GameManager {
    private GameListener mListener;
    private boolean currentPlayer = SebseGhatar.RED_PLAYER;

    @Inject
    public LocalGameManager() {
    }

    @Override
    public boolean isYourTurn() {
        return true;
    }

    @Override
    public void cellClickedAt(int row, int col, int layer) {
        mListener.changeItemAt(row, col, layer, currentPlayer);
        currentPlayer = !currentPlayer;
    }

    @Override
    public void dispose() {
        mListener = null;
    }

    @Override
    public void sendMessage(String msg) {

    }

    @Override
    public void start(GameListener gameListener) {
        this.mListener = gameListener;
        currentPlayer = SebseGhatar.RED_PLAYER;
    }
}
