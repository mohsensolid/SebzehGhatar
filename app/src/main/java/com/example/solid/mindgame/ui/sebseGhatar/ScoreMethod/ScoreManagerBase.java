/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

import com.example.solid.mindgame.Utils.PersistenceContract;

public abstract class ScoreManagerBase {
    private static final int ALL_PIECES = 24;
    private Step[] ALL_DIRECTIONS = new Step[]{};
    private int[][] board = new int[][]{
            {-1, 0, 0, -1, 0, 0, -1},
            {0, -1, 0, -1, 0, -1, 0},
            {0, 0, -1, -1, -1, 0, 0},
            {-1, -1, -1, 8, -1, -1, -1},
            {0, 0, -1, -1, -1, 0, 0},
            {0, -1, 0, -1, 0, -1, 0},
            {-1, 0, 0, -1, 0, 0, -1},
    };
    private int blueScore = 0;
    private int redScore = 0;

    void init() {
        this.ALL_DIRECTIONS = directions();
    }

    protected void cellChangeAt(int row, int col, int layer, boolean player) {
        this.blueScore = 0;
        this.redScore = 0;
        int mRow = (layer * row) + 3 - layer;
        int mCol = (layer * col) + 3 - layer;
        board[mRow][mCol] = player ? PersistenceContract.SebseGhatar.RED_PLAYER_NUMBER : PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER;
        checkForScore();
    }

    private void checkForScore() {
        int done = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (isFill(i, j)) {
                    isPoint(i, j);
                    done++;
                }
            }
        }
        updateResult(blueScore, redScore);
        if (done == ALL_PIECES) {
            gameIsDone(blueScore, redScore);
        }
    }

    protected abstract Step[] directions();

    abstract void gameIsDone(int blueScore, int redScore);

    private void isPoint(int row, int col) {
        Step current = new Step(row, col);
        for (Step step : ALL_DIRECTIONS) {
            int res = checkDirection(current, step);
            if (res == PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER * 3) {
                blueScore++;
            } else if (res == PersistenceContract.SebseGhatar.RED_PLAYER_NUMBER * 3) {
                redScore++;
            }
        }
    }

    protected abstract void updateResult(int blueScore, int redScore);

    boolean isFill(int row, int col) {
        return board[row][col] != 0 && board[row][col] != -1 && board[row][col] != 8;
    }

    private boolean isValidStep(int step) {
        return step > 0 && step < 7;
    }

    int checkDirection(Step current, Step step) {
        int sum = board[current.row][current.col];
        for (int row = current.row + step.row, col = current.col + step.col;
             isValidStep(row) && isValidStep(col); row += step.row, col += step.col) {
            int player = board[row][col];
            if (player == 8)
                return sum;
            if (player != 0 && player != -1) {
                sum += player;
            }
        }
        return sum;
    }

    protected void resetTheGame() {
        board = new int[][]{
                {-1, 0, 0, -1, 0, 0, -1},
                {0, -1, 0, -1, 0, -1, 0},
                {0, 0, -1, -1, -1, 0, 0},
                {-1, -1, -1, 8, -1, -1, -1},
                {0, 0, -1, -1, -1, 0, 0},
                {0, -1, 0, -1, 0, -1, 0},
                {-1, 0, 0, -1, 0, 0, -1},
        };
        blueScore = 0;
        redScore = 0;
        updateResult(0, 0);
    }

}
