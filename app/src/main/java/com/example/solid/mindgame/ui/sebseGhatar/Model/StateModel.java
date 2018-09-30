/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateModel {
    @Expose
    @SerializedName("state")
    private String State;
    @Expose
    @SerializedName("user")
    private boolean User;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public boolean isUser() {
        return User;
    }

    public void setUser(boolean user) {
        User = user;
    }
}
