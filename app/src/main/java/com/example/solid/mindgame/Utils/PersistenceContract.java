/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.Utils;

public final class PersistenceContract {


    private PersistenceContract() {
    }


    public static abstract class SebseGhatar {
        public static final boolean RED_PLAYER = true;
        public static final boolean BLUE_PLAYER = false;
        public static final int RED_PLAYER_NUMBER = 2;
        public static final int BLUE_PLAYER_NUMBER = 5;
    }

    public static abstract class GameMethod {
        public static final String INIT = "init";
        public static final String MOVE = "move";
        public static final String MESSAGE = "message";
        public static final String STATE = "state";
        public static final String STATE_DISCONNECTED = "disconnected";
    }

}
