/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.SebseGhatarPresenter;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.GameListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verify;


public class DefaultScoreTest {
    private Step[] DEFUALT_TEST_DIRECTIONS = new Step[]{
            new Step(1, 0),
            new Step(0, 1)
    };
    private int[][] DEFAULT_BOARD = new int[][]{
            {-1, 0, 0, -1, 0, 0, -1},
            {0, -1, 0, -1, 0, -1, 0},
            {0, 0, -1, -1, -1, 0, 0},
            {-1, -1, -1, 8, -1, -1, -1},
            {0, 0, -1, -1, -1, 0, 0},
            {0, -1, 0, -1, 0, -1, 0},
            {-1, 0, 0, -1, 0, 0, -1},
    };
    @Mock
    private SebseGhatarPresenter presenter;
    private DefaultScore defaultScore;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        defaultScore = new DefaultScore();
        defaultScore.start(presenter);
    }

    @Test
    public void cellChangeAt() {
        defaultScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        verify(presenter).blueScore(0);
        verify(presenter).redScore(0);
    }

    @Test
    public void threeCellWithSameColorInSameRowIsPoint() throws Exception {
        defaultScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        defaultScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        defaultScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 1);
    }

    @Test
    public void threeCellWithDifferentColorInSameRowIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        defaultScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        defaultScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellWithSameColorInSameColumnIsPoint() throws Exception {
        defaultScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        defaultScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        defaultScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 1);
    }

    @Test
    public void threeCellWithDifferentColorInSameColumnIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInColumnInDifferentLayerIsPoint() throws Exception {
        defaultScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDifferentColorCellInColumnInDifferentLayerIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 1, 3, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInRowInDifferentLayerIsPoint() throws Exception {
        defaultScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDifferentColorCellInRowInDifferentLayerIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 3, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInRowInDifferentLayerIsPointBothSide() throws Exception {
        defaultScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        defaultScore.cellChangeAt(1, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 2);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInColumnInDifferentLayerIsPointBothSide() throws Exception {
        defaultScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        defaultScore.cellChangeAt(2, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 2);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInColumnAfterHoleIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInRowAfterHoleIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(1, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInTopLeftDiagonalIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(0, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInTopRightDiagonalIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(0, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }
    @Test
    public void threeCellInBottomLeftDiagonalIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(2, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }
    @Test
    public void threeCellInBottomRightDiagonalIsNotPoint() throws Exception {
        defaultScore.cellChangeAt(2, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        defaultScore.cellChangeAt(2, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }
    @Test
    public void start() throws Exception {
        defaultScore.start(presenter);
        GameListener gameListener = getListner();
        Assert.assertSame(gameListener, presenter);
    }

    @Test
    public void dispose() throws Exception {
        defaultScore.dispose();
        GameListener gameListener = getListner();
        Assert.assertNull(gameListener);
    }

    @Test
    public void directions()  {
        Step[] steps = defaultScore.directions();
        Assert.assertArrayEquals(steps, DEFUALT_TEST_DIRECTIONS);
    }

    @Test
    public void gameIsDone() {
        defaultScore.gameIsDone(0, 0);
        verify(presenter).gameIsEnded(0, 0);
    }

    @Test
    public void updateResult() {
        defaultScore.updateResult(0, 0);
        verify(presenter).redScore(0);
        verify(presenter).blueScore(0);
    }

    @Test
    public void resetTheGame() throws Exception {
        defaultScore.resetTheGame();
        int[][] testBorad = getboard();
        verify(presenter).redScore(0);
        verify(presenter).blueScore(0);
        Assert.assertArrayEquals(testBorad, DEFAULT_BOARD);
    }

    private GameListener getListner() throws Exception {
        Field fieldisGameStarted = defaultScore.getClass().getDeclaredField("mListener");
        fieldisGameStarted.setAccessible(true);
        return (GameListener) fieldisGameStarted.get(defaultScore);
    }

    private int[][] getboard() throws Exception {
        Field fieldisGameStarted = defaultScore.getClass().getSuperclass().getDeclaredField("board");
        fieldisGameStarted.setAccessible(true);
        return (int[][]) fieldisGameStarted.get(defaultScore);
    }

    private int getBlueScore() throws Exception {
        Field fieldisGameStarted = defaultScore.getClass().getSuperclass().getDeclaredField("blueScore");
        fieldisGameStarted.setAccessible(true);
        return (int) fieldisGameStarted.get(defaultScore);
    }

    private int getRedScore() throws Exception {
        Field fieldisGameStarted = defaultScore.getClass().getSuperclass().getDeclaredField("redScore");
        fieldisGameStarted.setAccessible(true);
        return (int) fieldisGameStarted.get(defaultScore);
    }
}