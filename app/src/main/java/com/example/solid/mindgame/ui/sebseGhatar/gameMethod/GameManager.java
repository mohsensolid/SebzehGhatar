/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod;

public interface GameManager {
   boolean isYourTurn();

    void cellClickedAt(int row, int col, int layer);
    void dispose();

    void sendMessage(String msg);

    void start(GameListener gameListener);
}
