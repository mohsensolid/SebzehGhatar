/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

import android.util.Log;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.Model.AiMove;

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

    protected int[][] getBoard() {
        int[][] boardCopy = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }

    protected void cellChangeAt(int row, int col, int layer, boolean player) {
        this.blueScore = 0;
        this.redScore = 0;
        int mRow = (layer * row) + 3 - layer;
        int mCol = (layer * col) + 3 - layer;
        board[mRow][mCol] = player ? PersistenceContract.SebseGhatar.RED_PLAYER_NUMBER : PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER;
        checkForScore(this.board);
    }

    public int EvaluateBoard(int[][] board) {
        int aiScore = 0;
        int playerScore = 0;
        int aiTwo = 0;
        int playerTwo = 0;
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                if ((board[row][col] == 2 || board[row][col] == 5)) {
                    Step current = new Step(row, col);
                    for (Step step : ALL_DIRECTIONS) {
                        int res = checkDirection(current, step, board);
                        if (res == PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER * 3) {
                            aiScore++;
                        } else if (res == PersistenceContract.SebseGhatar.RED_PLAYER_NUMBER * 3) {
                            playerScore++;
                        }
                        if (res == PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER * 2) {
                            aiTwo++;
                        } else if (res == PersistenceContract.SebseGhatar.RED_PLAYER_NUMBER * 2) {
                            playerTwo++;
                        }
                    }
                }
            }
        }
        return ((aiScore * 600) + (aiTwo * 10)) - ((playerScore * 1200) + (playerTwo * 10));
    }

    private void checkForScore(int[][] board) {
        int done = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (isSelected(i, j)) {
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
            int res = checkDirection(current, step, board);
            if (res == PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER * 3) {
                blueScore++;
            } else if (res == PersistenceContract.SebseGhatar.RED_PLAYER_NUMBER * 3) {
                redScore++;
            }
        }
    }

    protected abstract void updateResult(int blueScore, int redScore);

    boolean isSelected(int row, int col) {
        return board[row][col] != 0 && board[row][col] != -1 && board[row][col] != 8;
    }

    private boolean isValidStep(int step) {
        return step > 0 && step < 7;
    }

    int checkDirection(Step current, Step step, int[][] board) {
        int sum = 0;
        if (current.row == 0 && current.col == 0) {
            int value = 0;
            for (int i = current.row; i < 7; i++) {
                if (step.col == 1) {
                    value = board[0][i];
                } else if (step.row == 1) {
                    value = board[i][0];
                }
                if (value != 0 && value != -1) {
                    sum += value;
                }
            }
            return sum;
        }
        sum = board[current.row][current.col];
        for (int row = current.row + step.row, col = current.col + step.col;
             isValidStep(row) && isValidStep(col); row += step.row, col += step.col) {
            int value = board[row][col];
            if (value == 8)
                return sum;
            if (value != 0 && value != -1) {
                sum += value;
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
