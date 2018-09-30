/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod;

public interface GameListener {
    void changeItemAt(int row, int col, int layer, boolean player);
    void newMessageRecive(String messageText, boolean userMessage);
    void opponentFind();
    void userDisconnected(boolean user);
}
