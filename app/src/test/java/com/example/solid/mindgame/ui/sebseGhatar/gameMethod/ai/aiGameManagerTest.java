/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod.ai;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod.DefaultScore;
import com.example.solid.mindgame.ui.sebseGhatar.SebseGhatarPresenter;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.OnlineGameManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.example.solid.mindgame.Utils.PersistenceContract.SebseGhatar.BLUE_PLAYER;
import static com.example.solid.mindgame.Utils.PersistenceContract.SebseGhatar.RED_PLAYER;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class aiGameManagerTest {

    @Mock
    private
    SebseGhatarPresenter mPresenter;
    DefaultScore scoreManager;
    private aiGameManager gameManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        scoreManager = new DefaultScore();
        scoreManager.start(mPresenter);
        gameManager = new aiGameManager(scoreManager);
        gameManager.start(mPresenter);
    }


    @Test
    public void isYourTurn() {
    }

    @Test
    public void cellClickedAt() {
    }

    @Test
    public void dispose() {
    }

    @Test
    public void sendMessage() {
    }

    @Test
    public void start() {
    }
}