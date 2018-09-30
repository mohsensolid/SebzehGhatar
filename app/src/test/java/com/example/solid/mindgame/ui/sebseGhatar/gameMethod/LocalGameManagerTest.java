/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.SebseGhatarPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class LocalGameManagerTest {

    @Mock
    private
    SebseGhatarPresenter mPresenter;
    private LocalGameManager localGameManager;
    private Field field;
    private boolean currentPlayer;
    private GameListener gameListener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        localGameManager = new LocalGameManager();
        localGameManager.start(mPresenter);
        field = localGameManager.getClass().getDeclaredField("currentPlayer");
        field.setAccessible(true);
        currentPlayer = (boolean) field.get(localGameManager);

        Field fieldListener = localGameManager.getClass().getDeclaredField("mListener");
        fieldListener.setAccessible(true);
        gameListener = (GameListener) fieldListener.get(localGameManager);

    }

    @Test
    public void isYourTurn() {
        boolean turn = localGameManager.isYourTurn();
        Assert.assertTrue(turn);
    }

    @Test
    public void cellClickedAt() throws IllegalAccessException {
        localGameManager.cellClickedAt(0, 0, 0);
        verify(mPresenter).changeItemAt(0, 0, 0, currentPlayer);
        boolean nextPlayer = (boolean) field.get(localGameManager);
        Assert.assertTrue(currentPlayer !=nextPlayer);
        localGameManager.cellClickedAt(1, 1, 1);
        verify(mPresenter).changeItemAt(1, 1, 1, nextPlayer);
    }

    @Test
    public void dispose() throws NoSuchFieldException, IllegalAccessException {
        Assert.assertNotNull(gameListener);
        localGameManager.dispose();
        Field field = localGameManager.getClass().getDeclaredField("mListener");
        field.setAccessible(true);
        GameListener listener = (GameListener) field.get(localGameManager);
        Assert.assertNull(listener);
    }
    @Test
    public void start() {
        Assert.assertEquals(currentPlayer, PersistenceContract.SebseGhatar.RED_PLAYER);
    }
}