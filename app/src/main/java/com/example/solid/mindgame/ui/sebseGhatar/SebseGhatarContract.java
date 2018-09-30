/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar;

import android.content.Context;

import com.example.solid.mindgame.base.BasePresenter;
import com.example.solid.mindgame.base.BaseView;

public interface SebseGhatarContract {
    interface View extends BaseView {
        Context context();
        void changeItemAt(int row, int col, int layer, boolean player);
        void showRedScore(int score);
        void showBlueScore(int score);
        void addNewMessage(String messageText, boolean userMessage);
        void resetTheBoard();
        void toggleGameOptions();
        void displayChatBox();
        void hideChatBox();
        void clearChat();
        void showOpponentFinder();
        void hideOpponentFinde();
        void gameIsEnded(int blueScore, int redScore);
    }
    interface Presenter extends BasePresenter<View> {
        boolean isYourTurn();
        void cellClickedAt(int row,int col,int layer);
        void sendMessage(String msg);
        void resetTheGame();
        void onlineGame();
        void twoPlayerGame();
    }
}
