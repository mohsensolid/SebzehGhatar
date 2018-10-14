/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

import com.example.solid.mindgame.ui.sebseGhatar.Model.AiMove;

public interface ScoreManager {
    void resetTheGame();
    void  cellChangeAt(int row, int col, int layer, boolean player);
    void start(ScoreListener listener);
    void dispose();
    int EvaluateBoard(int[][] board);
    int[][] getBoard();
}
