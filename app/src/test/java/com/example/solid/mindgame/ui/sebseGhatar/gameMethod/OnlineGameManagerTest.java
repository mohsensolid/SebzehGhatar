/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.gameMethod;

import com.example.solid.mindgame.Utils.PersistenceContract;
import com.example.solid.mindgame.ui.sebseGhatar.Model.ChatModel;
import com.example.solid.mindgame.ui.sebseGhatar.Model.MoveData;
import com.example.solid.mindgame.ui.sebseGhatar.Model.SocketRequest;
import com.example.solid.mindgame.ui.sebseGhatar.SebseGhatarPresenter;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketCall;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketContract;
import com.example.solid.mindgame.ui.sebseGhatar.gameMethod.webSocket.WebSocketImpl;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import static com.example.solid.mindgame.Utils.PersistenceContract.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class OnlineGameManagerTest {

    private static final String MESSAGE = "message for test";
    @Mock
    private
    SebseGhatarPresenter presenter;
    @Mock
    private
    WebSocketContract webSocketContract;
    private OnlineGameManager onlineGameManager;
    private GameListener listener;
    private Gson getGson() {
        return new Gson();
    }


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        onlineGameManager = new OnlineGameManager(getGson(),webSocketContract);
        onlineGameManager.start(presenter);
        listener = getListener();
    }

    @Test
    public void isYourTurn()  {
        onlineGameManager.yourTurn(SebseGhatar.RED_PLAYER);
        onlineGameManager.lastTurn(SebseGhatar.BLUE_PLAYER);
        onlineGameManager.gameIsStarted();
        Assert.assertTrue(onlineGameManager.isYourTurn());
    }

    @Test
    public void notYourTurn() throws Exception {
        onlineGameManager.yourTurn(SebseGhatar.RED_PLAYER);
        onlineGameManager.lastTurn(SebseGhatar.RED_PLAYER);
        onlineGameManager.gameIsStarted();
        Assert.assertTrue(!onlineGameManager.isYourTurn());

    }

    @Test
    public void notYourTurnIfGameNotStart() {
        onlineGameManager.yourTurn(SebseGhatar.RED_PLAYER);
        onlineGameManager.lastTurn(SebseGhatar.BLUE_PLAYER);
        Assert.assertTrue(!onlineGameManager.isYourTurn());
    }

    @Test
    public void cellClickedAt() throws Exception {
        onlineGameManager.cellClickedAt(0, 0, 0);
        MoveData move = new MoveData(0, 0, 0, getyourColor());
        SocketRequest request = new SocketRequest(PersistenceContract.GameMethod.MOVE, getGson().toJson(move));
        verify(webSocketContract).send(getGson().toJson(request));
    }

    @Test
    public void dispose() throws Exception {
        onlineGameManager.start(presenter);
        onlineGameManager.dispose();
        verify(webSocketContract).disconnect();
        GameListener listener = getListener();
        Assert.assertNull(listener);
    }

    @Test
    public void sendMessage() {
        onlineGameManager.sendMessage(MESSAGE);
        ChatModel chatModel = new ChatModel(MESSAGE, true);
        SocketRequest request = new SocketRequest(PersistenceContract.GameMethod.MESSAGE, getGson().toJson(chatModel));
        verify(webSocketContract).send(getGson().toJson(request));
    }

    @Test
    public void start() throws Exception {
        Assert.assertNotNull(listener);
        verify(webSocketContract).start(onlineGameManager);
    }

    @Test
    public void newMove() {
        onlineGameManager.newMove(0, 0, 0, true);
        verify(presenter).changeItemAt(0, 0, 0, true);
    }

    @Test
    public void yourTurn() throws Exception {
        onlineGameManager.yourTurn(SebseGhatar.RED_PLAYER);
        boolean userColor = getyourColor();
        Assert.assertEquals(userColor, SebseGhatar.RED_PLAYER);
    }

    @Test
    public void lastTurn() throws Exception {
        onlineGameManager.lastTurn(SebseGhatar.BLUE_PLAYER);
        boolean lastTurn = getLastTurn();
        Assert.assertTrue(SebseGhatar.BLUE_PLAYER == lastTurn);
    }

    @Test
    public void newMessage() throws Exception {
        onlineGameManager.yourTurn(SebseGhatar.BLUE_PLAYER);
        onlineGameManager.newMessage(MESSAGE, SebseGhatar.RED_PLAYER);
        boolean yourColor = getyourColor();
        verify(presenter).newMessageRecive(MESSAGE, yourColor == SebseGhatar.RED_PLAYER);
    }

    @Test
    public void gameIsStarted() throws Exception {
        onlineGameManager.gameIsStarted();
        boolean isGameStarted = isGameStarted();
        Assert.assertTrue(isGameStarted);
        verify(presenter).opponentFind();
    }

    @Test
    public void userDisconnected() throws Exception {
        onlineGameManager.yourTurn(SebseGhatar.BLUE_PLAYER);
        onlineGameManager.userDisconnected(SebseGhatar.RED_PLAYER);
        boolean yourColor = getyourColor();
        verify(presenter).userDisconnected(SebseGhatar.RED_PLAYER == yourColor);
    }

    private boolean getyourColor() throws Exception {
        Field fieldisGameStarted = onlineGameManager.getClass().getDeclaredField("yourColor");
        fieldisGameStarted.setAccessible(true);
        return (boolean) fieldisGameStarted.get(onlineGameManager);
    }

    private boolean getLastTurn() throws Exception {
        Field fieldlastTurn = onlineGameManager.getClass().getDeclaredField("lastTurn");
        fieldlastTurn.setAccessible(true);
        return (boolean) fieldlastTurn.get(onlineGameManager);
    }

    private GameListener getListener() throws Exception {
        Field fieldlastTurn = onlineGameManager.getClass().getDeclaredField("mListener");
        fieldlastTurn.setAccessible(true);
        return (GameListener) fieldlastTurn.get(onlineGameManager);
    }


    private boolean isGameStarted() throws Exception {
        Field fieldisGameStarted = onlineGameManager.getClass().getDeclaredField("isGameStarted");
        fieldisGameStarted.setAccessible(true);
        return (boolean) fieldisGameStarted.get(onlineGameManager);

    }
}