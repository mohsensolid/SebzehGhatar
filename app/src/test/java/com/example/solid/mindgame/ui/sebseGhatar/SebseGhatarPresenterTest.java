/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar;

import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DefaultScore;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DiagonalScore;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.ScoreManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.GameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.LocalGameManager;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.OnlineGameManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verify;

public class SebseGhatarPresenterTest {

    @Mock
    private
    SebseGhatarContract.View sebseGhatarContract;
    @Mock
    private LocalGameManager localGameManager;
    @Mock
    private OnlineGameManager onlineGameManager;
    @Mock
    private
    DefaultScore defaultScore;
    ScoreManager scoreManager;
    private SebseGhatarPresenter presenter;
    private Field field;
    @Mock
    private
    DiagonalScore diagonalScore;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SebseGhatarPresenter(onlineGameManager, localGameManager, defaultScore, diagonalScore);
        presenter.attachView(sebseGhatarContract);
        field = presenter.getClass().getDeclaredField("gameManager");
        field.setAccessible(true);
    }

    @Test
    public void test_click_two_player_clearborad() throws IllegalAccessException {
        presenter.twoPlayerGame();
        GameManager gameManager = (GameManager) field.get(presenter);
        verify(sebseGhatarContract).hideOpponentFinde();
        verify(sebseGhatarContract).clearChat();
        verify(sebseGhatarContract).hideChatBox();
        verify(sebseGhatarContract).resetTheBoard();
        verify(sebseGhatarContract).toggleGameOptions();
        verify(sebseGhatarContract).resetTheBoard();
        verify(defaultScore).resetTheGame();
        Assert.assertTrue(gameManager instanceof LocalGameManager);
    }

    @Test
    public void test_click_online_player_clearBord() throws IllegalAccessException {
        presenter.onlineGame();
        GameManager gameManager = (GameManager) field.get(presenter);
        verify(sebseGhatarContract).displayChatBox();
        verify(sebseGhatarContract).clearChat();
        verify(sebseGhatarContract).toggleGameOptions();
        verify(sebseGhatarContract).resetTheBoard();
        verify(sebseGhatarContract).showOpponentFinder();
        verify(defaultScore).resetTheGame();
        Assert.assertTrue(gameManager instanceof OnlineGameManager);
    }

    @Test
    public void test_resetTheGame() {
        presenter.resetTheGame();
        verify(sebseGhatarContract).clearChat();
        verify(sebseGhatarContract).toggleGameOptions();
        verify(sebseGhatarContract).resetTheBoard();
        verify(defaultScore).resetTheGame();
    }

    @Test
    public void test_resetTheGameOffline() throws IllegalAccessException, NoSuchFieldException {
        presenter.resetTheGame();
        presenter.twoPlayerGame();
        field = presenter.getClass().getDeclaredField("gameManager");
        field.setAccessible(true);
        GameManager gameManager = (GameManager) field.get(presenter);
        Assert.assertNotNull(gameManager);
        verify(localGameManager).start(presenter);
    }

    @Test
    public void test_resetTheGameOnline() throws IllegalAccessException, NoSuchFieldException {
        presenter.resetTheGame();
        presenter.onlineGame();
        field = presenter.getClass().getDeclaredField("gameManager");
        field.setAccessible(true);
        GameManager gameManager = (GameManager) field.get(presenter);
        Assert.assertNotNull(gameManager);
        verify(onlineGameManager).start(presenter);
    }

    @Test
    public void test_IsyourTurn() {
        presenter.twoPlayerGame();
        presenter.isYourTurn();
        verify(localGameManager).isYourTurn();
    }

    @Test
    public void test_IsDisopeAttheEnd() throws IllegalAccessException {
        presenter.twoPlayerGame();
        presenter.dispose();
        GameManager gameManager = (GameManager) field.get(presenter);
        verify(localGameManager).dispose();
        verify(defaultScore).dispose();
        Assert.assertNull(gameManager);

    }

    @Test
    public void test_cellChangeshowError() {
        presenter.cellClickedAt(0, 0, 0);
        verify(sebseGhatarContract).showError("گزینه ای انتخاب نشده است");
    }

    @Test
    public void test_cellChangeInovkeInterface() {
        presenter.twoPlayerGame();
        presenter.cellClickedAt(0, 0, 0);
        verify(localGameManager).cellClickedAt(0, 0, 0);
    }

    @Test
    public void test_sendMessageToGamemanager() {
        presenter.twoPlayerGame();
        presenter.sendMessage("salam");
        verify(localGameManager).sendMessage("salam");
    }

    @Test
    public void test_changeItemAt() {
        presenter.changeItemAt(0, 0, 0, false);
        verify(sebseGhatarContract).changeItemAt(0, 0, 0, false);
        verify(defaultScore).cellChangeAt(0, 0, 0, false);
    }

    @Test
    public void test_newMessageRecive() {
        presenter.newMessageRecive("salam", false);
        verify(sebseGhatarContract).addNewMessage("salam", false);
    }

    @Test
    public void test_OpponetHideAfterFind() throws IllegalAccessException {
        presenter.opponentFind();
        verify(sebseGhatarContract).hideOpponentFinde();
    }

    @Test
    public void test_userDisconected() {
        presenter.userDisconnected(true);
        verify(sebseGhatarContract).addNewMessage("اتصال شما با سرور قطع شد", true);
        presenter.userDisconnected(false);
        verify(sebseGhatarContract).addNewMessage("حریف بازی را ترک کرد...", false);
    }

    @Test
    public void test_gameIsEnded() {
        presenter.gameIsEnded(0, 0);
        verify(sebseGhatarContract).gameIsEnded(0, 0);
    }

    @Test
    public void test_blueScore() {
        presenter.blueScore(0);
        verify(sebseGhatarContract).showBlueScore(0);
    }

    @Test
    public void test_redScore() {
        presenter.redScore(0);
        verify(sebseGhatarContract).showRedScore(0);
    }
}