/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitData {
    @Expose
    @SerializedName("turn")
    private boolean UserTurn;
    public boolean UserTurn() {
        return UserTurn;
    }

    public void setUserTurn(boolean userTurn) {
        UserTurn = userTurn;
    }
}
