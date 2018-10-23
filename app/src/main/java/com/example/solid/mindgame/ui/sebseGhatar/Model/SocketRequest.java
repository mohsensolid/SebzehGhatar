/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.Expose;
@Keep

public class SocketRequest {

    @Expose
    private String type;
    @Expose
    private String data;

    public SocketRequest(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
