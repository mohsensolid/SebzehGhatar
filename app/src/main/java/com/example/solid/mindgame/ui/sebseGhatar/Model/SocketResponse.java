/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocketResponse {
    @Expose
    @SerializedName("type")
    private String Type;
    @Expose
    @SerializedName("data")
    private String Data;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "SocketResponse{" +
                "Type='" + Type + '\'' +
                ", Data='" + Data + '\'' +
                '}';
    }
}
