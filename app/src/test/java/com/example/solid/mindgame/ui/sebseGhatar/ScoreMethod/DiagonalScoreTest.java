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

public class DiagonalScoreTest {
    private Step[] DEFUALT_TEST_DIRECTIONS = new Step[]{
            new Step(1, 0),
            new Step(0, 1),
            new Step(1, 1),
            new Step(1, -1),
            new Step(-1, 1)
    };
    int[][] DEFALUT_BORAD = new int[][]{
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
    private DiagonalScore diagonalScore;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        diagonalScore = new DiagonalScore();
        diagonalScore.start(presenter);
    }

    @Test
    public void cellChangeAt() {
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        verify(presenter).blueScore(0);
        verify(presenter).redScore(0);
    }

    @Test
    public void threeCellWithSameColorInSameRowIsPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 1);
    }

    @Test
    public void threeCellWithDifferentColorInSameRowIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellWithSameColorInSameColumnIsPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 1);
    }

    @Test
    public void threeCellWithDifferentColorInSameColumnIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInColumnInDifferentLayerIsPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDifferentColorCellInColumnInDifferentLayerIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 3, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInRowInDifferentLayerIsPoint() throws Exception {
        diagonalScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDifferentColorCellInRowInDifferentLayerIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 3, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInRowInDifferentLayerIsPointBothSide() throws Exception {
        diagonalScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        diagonalScore.cellChangeAt(1, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 2);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeSameColorCellInColumnInDifferentLayerIsPointBothSide() throws Exception {
        diagonalScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        diagonalScore.cellChangeAt(2, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 2);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInColumnAfterHoleIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 1, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 1, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 1, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }


    @Test
    public void threeCellInRowAfterHoleIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(1, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(1, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void towCellInTopLeftDiagnoseIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void towCellInTopRightDiagnoseIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void towCellInBottomLeftDiagnoseIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void towCellInBottomRightDiagnoseIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInTopLeftDiagonalIsPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDiffCellInTopLeftDiagonalIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void sixCellInTopLeftSkipHoleDiagonalIs2Point() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        diagonalScore.cellChangeAt(2, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 2);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void sixCellInTopRightSkipHoleDiagonalIs2Point() throws Exception {
        diagonalScore.cellChangeAt(0, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        diagonalScore.cellChangeAt(2, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 2);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInTopRightDiagonalIsPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDiffCellInTopRightDiagonalIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInBottomLeftDiagonalIsPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDiffCellInBottomLeftDiagonalIsPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 0, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeCellInBottomRightDiagonalIsPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 1);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void threeDiffCellInBottomRightDiagonalIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 2, 3, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 1, PersistenceContract.SebseGhatar.BLUE_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void BottomRightSkipHoleIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void BottomLeftSkipHoleIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(2, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void TopRightSkipHoleIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 2, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void TopLeftSkupHoleIsNotPoint() throws Exception {
        diagonalScore.cellChangeAt(0, 0, 2, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(0, 0, 1, PersistenceContract.SebseGhatar.RED_PLAYER);
        diagonalScore.cellChangeAt(2, 2, 1, PersistenceContract.SebseGhatar.RED_PLAYER);

        int blueScore = getBlueScore();
        int redScore = getRedScore();
        Assert.assertEquals(redScore, 0);
        Assert.assertEquals(blueScore, 0);
    }

    @Test
    public void start() throws Exception {
        diagonalScore.start(presenter);
        GameListener gameListener = getListner();
        Assert.assertSame(gameListener, presenter);
    }

    @Test
    public void dispose() throws Exception {
        diagonalScore.dispose();
        GameListener gameListener = getListner();
        Assert.assertNull(gameListener);
    }

    @Test
    public void directions() throws Exception {
        Step[] steps = diagonalScore.directions();
        Assert.assertArrayEquals(steps, DEFUALT_TEST_DIRECTIONS);
    }

    @Test
    public void gameIsDone() {
        diagonalScore.gameIsDone(0, 0);
        verify(presenter).gameIsEnded(0, 0);
    }

    @Test
    public void updateResult() {
        diagonalScore.updateResult(0, 0);
        verify(presenter).redScore(0);
        verify(presenter).blueScore(0);
    }

    @Test
    public void resetTheGame() throws Exception {
        diagonalScore.resetTheGame();
        int[][] testBorad = getboard();
        verify(presenter).redScore(0);
        verify(presenter).blueScore(0);
        Assert.assertArrayEquals(testBorad, DEFALUT_BORAD);
    }

    private GameListener getListner() throws Exception {
        Field fieldisGameStarted = diagonalScore.getClass().getDeclaredField("mListener");
        fieldisGameStarted.setAccessible(true);
        return (GameListener) fieldisGameStarted.get(diagonalScore);
    }

    private int[][] getboard() throws Exception {
        Field fieldisGameStarted = diagonalScore.getClass().getSuperclass().getDeclaredField("board");
        fieldisGameStarted.setAccessible(true);
        return (int[][]) fieldisGameStarted.get(diagonalScore);
    }

    private int getBlueScore() throws Exception {
        Field fieldisGameStarted = diagonalScore.getClass().getSuperclass().getDeclaredField("blueScore");
        fieldisGameStarted.setAccessible(true);
        return (int) fieldisGameStarted.get(diagonalScore);
    }

    private int getRedScore() throws Exception {
        Field fieldisGameStarted = diagonalScore.getClass().getSuperclass().getDeclaredField("redScore");
        fieldisGameStarted.setAccessible(true);
        return (int) fieldisGameStarted.get(diagonalScore);
    }

}