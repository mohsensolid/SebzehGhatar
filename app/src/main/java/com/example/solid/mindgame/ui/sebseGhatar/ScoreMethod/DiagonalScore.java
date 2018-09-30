/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

public class DiagonalScore extends ScoreManagerBase implements ScoreManager {
    private ScoreListener mListener;

    public DiagonalScore() {
        super.init();
    }


    @Override
    public void resetTheGame() {
        super.resetTheGame();
    }

    @Override
    public void cellChangeAt(int row, int col, int layer, boolean player) {
        super.cellChangeAt(row, col, layer, player);
    }

    @Override
    protected Step[] directions() {
        return new Step[]{
                new Step(1, 0),
                new Step(0, 1),
                new Step(1, 1),
                new Step(1, -1),
                new Step(-1,1)
        };
    }

    @Override
    void gameIsDone(int blueScore, int redScore) {
        mListener.gameIsEnded(blueScore, redScore);
    }

    @Override
    protected void updateResult(int blueScore, int redScore) {
        mListener.redScore(redScore);
        mListener.blueScore(blueScore);
    }

    @Override
    public void start(ScoreListener listener) {
        this.mListener = listener;
    }

    @Override
    public void dispose() {
        mListener = null;
    }
}
