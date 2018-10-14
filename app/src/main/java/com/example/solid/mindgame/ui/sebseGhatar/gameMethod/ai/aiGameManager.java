/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod.ai;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.Model.AiMove;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.ScoreManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.GameListener;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.GameManager;

public class aiGameManager implements GameManager {
    private boolean isUserTurn = true;
    private GameListener mListener;
    private ScoreManager scoreManager;

    public aiGameManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    @Override
    public boolean isYourTurn() {
        return isUserTurn;
    }

    @Override
    public void cellClickedAt(int row, int col, int layer) {
        mListener.changeItemAt(row, col, layer, isUserTurn);
        this.isUserTurn = false;
        int[][] board =  scoreManager.getBoard();
        MiniMax miniMax = new MiniMax(scoreManager);
        AiMove aiMove = miniMax.bestMove(board);
        mListener.changeItemAt(aiMove.getRow(), aiMove.getCol(), aiMove.getLayer(), isUserTurn);
        this.isUserTurn = true;
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
    }
}
