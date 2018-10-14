/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod.ai;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.Model.AiMove;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.ScoreManager;


public class MiniMax {

    static int MAX = 1000;
    static int MIN = -1000;
    private ScoreManager scoreManager;

    public MiniMax(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public AiMove bestMove(int board[][]) {
        int bestValue = -9999;
        AiMove aiMove = null;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                for (int layer = 1; layer < 4; layer++) {
                    int mRow = (layer * row) + 3 - layer;
                    int mCol = (layer * col) + 3 - layer;
                    if (board[mRow][mCol] == -1) {
                        board[mRow][mCol] = PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER;
                        int moveValue = minimax(2, false, board, MIN, MAX);
                        board[mRow][mCol] = -1;
                        if (moveValue > bestValue) {
                            bestValue = moveValue;
                            aiMove = new AiMove(row, col, layer);
                        }
                    }
                }
            }
        }
        return aiMove;
    }

    private int minimax(int depth,
                        Boolean maxPlayer,
                        int board[][], int alpha,
                        int beta) {
        if (depth == 0) {
            return scoreManager.EvaluateBoard(board);
        }
        if (maxPlayer) {
            int best = MIN;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    for (int layer = 1; layer < 4; layer++) {
                        int mRow = (layer * row) + 3 - layer;
                        int mCol = (layer * col) + 3 - layer;
                        if (board[mRow][mCol] == -1) {
                            board[mRow][mCol] = PersistenceContract.SebseGhatar.BLUE_PLAYER_NUMBER;
                            int res = minimax(depth - 1, false, board, alpha, beta);
                            board[mRow][mCol] = -1;
                            best = Math.max(best, res);
                            alpha = Math.max(alpha, best);
                            if (beta <= alpha)
                                break;
                        }
                    }
                }
            }
            return best;
        } else {
            int best = MAX;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    for (int layer = 1; layer < 4; layer++) {
                        int mRow = (layer * row) + 3 - layer;
                        int mCol = (layer * col) + 3 - layer;
                        if (board[mRow][mCol] == -1) {
                            board[mRow][mCol] = PersistenceContract.SebseGhatar.RED_PLAYER_NUMBER;
                            int res = minimax(depth - 1, true, board, alpha, beta);
                            best = Math.min(best, res);
                            board[mRow][mCol] = -1;
                            beta = Math.min(beta, best);
                            if (beta <= alpha)
                                break;
                        }
                    }
                }
            }
            return best;
        }
    }
}
