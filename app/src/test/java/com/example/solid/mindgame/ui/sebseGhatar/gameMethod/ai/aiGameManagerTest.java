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
//    @Mock
//    private
    DefaultScore scoreManager;
    private aiGameManager gameManager;
    private DefaultScore scoreManager1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        scoreManager1 = new DefaultScore();
        scoreManager1.start(mPresenter);
        gameManager = new aiGameManager(scoreManager1);
        gameManager.start(mPresenter);
    }

    @Test
    public void aiPickEasyWin() {
        scoreManager1.cellChangeAt(2,2,3,RED_PLAYER);
        scoreManager1.cellChangeAt(2,2,2,BLUE_PLAYER);
//        Mockito.when(scoreManager.getBoard()).thenReturn(new int[][]{
//                {-1, 0, 0, -1, 0, 0, -1},
//                {0, -1, 0, -1, 0, -1, 0},
//                {0, 0, -1, -1, -1, 0, 0},
//                {-1, -1, -1, 8, -1, -1, -1},
//                {0, 0, -1, -1, -1, 0, 0},
//                {0, -1, 0, -1, 0, 5, 0},
//                {-1, 0, 0, -1, 0, 0, 2},
//        });
        gameManager.cellClickedAt(1, 2, 3);

        verify(mPresenter).changeItemAt(0, 2, 3, BLUE_PLAYER);
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