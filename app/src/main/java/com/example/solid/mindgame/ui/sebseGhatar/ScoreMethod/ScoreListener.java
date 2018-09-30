/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

public interface ScoreListener {
        void redScore(int score);
        void blueScore(int score);
        void gameIsEnded(int blueScore,int redScore);
}
