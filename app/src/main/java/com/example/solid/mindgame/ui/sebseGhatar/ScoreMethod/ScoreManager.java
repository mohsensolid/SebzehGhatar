/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

public interface ScoreManager {
    void resetTheGame();
    void  cellChangeAt(int row, int col, int layer, boolean player);
    void start(ScoreListener listener);
    void dispose();
}
