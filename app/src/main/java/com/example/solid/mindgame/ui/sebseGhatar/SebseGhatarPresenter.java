/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar;


import com.example.solid.mindgame.base.RxPresenter;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DefaultScore;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DiagonalScore;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.ScoreListener;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.ScoreManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.GameListener;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.GameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.LocalGameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.OnlineGameManager;

import javax.inject.Inject;

public class SebseGhatarPresenter extends RxPresenter<SebseGhatarContract.View> implements SebseGhatarContract.Presenter, GameListener, ScoreListener {

    private GameManager gameManager;
    private ScoreManager scoreManager;
    private DefaultScore defaultScore;
    private OnlineGameManager onlineGameManager;
    private LocalGameManager localGameManager;
    private DiagonalScore diagonalScore;
    @Inject
    public SebseGhatarPresenter(OnlineGameManager onlineGameManager, LocalGameManager localGameManager, DefaultScore defaultScore, DiagonalScore diagonalScore) {
        this.onlineGameManager = onlineGameManager;
        this.localGameManager = localGameManager;
        this.defaultScore = defaultScore;
        this.diagonalScore = diagonalScore;
        this.scoreManager = this.defaultScore;
        this.scoreManager.start(this);
    }

    @Override
    public void dispose() {
        if (gameManager != null)
            gameManager.dispose();
        scoreManager.dispose();
        this.gameManager = null;
    }

    @Override
    public boolean isYourTurn() {
        return gameManager == null || gameManager.isYourTurn();
    }

    @Override
    public void cellClickedAt(int row, int col, int layer) {
        if (gameManager != null) {
            gameManager.cellClickedAt(row, col, layer);
        } else {
            mView.showError("گزینه ای انتخاب نشده است");
        }
    }

    @Override
    public void sendMessage(String msg) {
        gameManager.sendMessage(msg);
    }

    @Override
    public void resetTheGame() {
        reset();
        if (gameManager != null) {
            gameManager.start(this);
        }
    }

    private void reset() {
        scoreManager.resetTheGame();
        mView.resetTheBoard();
        mView.toggleGameOptions();
        mView.clearChat();
    }

    @Override
    public void onlineGame() {
        reset();
        mView.showOpponentFinder();
        gameManager = onlineGameManager;
        gameManager.start(this);
        mView.displayChatBox();
    }

    @Override
    public void twoPlayerGame() {
        reset();
        gameManager = localGameManager;
        gameManager.start(this);
        mView.hideChatBox();
        mView.hideOpponentFinde();

    }

    @Override
    public void changeItemAt(int row, int col, int layer, boolean player) {
        mView.changeItemAt(row, col, layer, player);
        scoreManager.cellChangeAt(row, col, layer, player);
    }

    @Override
    public void newMessageRecive(String messageText, boolean userMessage) {
        mView.addNewMessage(messageText, userMessage);
    }

    @Override
    public void opponentFind() {
        mView.hideOpponentFinde();
    }

    @Override
    public void redScore(int score) {
        mView.showRedScore(score);
    }

    @Override
    public void blueScore(int score) {
        mView.showBlueScore(score);
    }

    @Override
    public void gameIsEnded(int blueScore, int redScore) {
        mView.gameIsEnded(blueScore, redScore);
    }

    @Override
    public void userDisconnected(boolean user) {
        mView.addNewMessage(!user ? "حریف بازی را ترک کرد..." : "اتصال شما با سرور قطع شد", user);
    }
}

