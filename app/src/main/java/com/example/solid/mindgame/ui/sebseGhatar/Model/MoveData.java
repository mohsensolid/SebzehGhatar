/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep

public class MoveData {
    @Expose
    @SerializedName("col")
    private int Col;
    @Expose
    @SerializedName("row")
    private int Row;
    @Expose
    @SerializedName("layer")
    private int layer;
    @Expose
    @SerializedName("player")
    private boolean player;

    public MoveData(int row, int col, int layer, boolean player) {
        this.Row = row;
        this.Col = col;
        this.layer = layer;
        this.player = player;
    }

    public int getCol() {
        return Col;
    }

    public void setCol(int col) {
        Col = col;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int row) {
        Row = row;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "MoveData{" +
                "Col=" + Col +
                ", Row=" + Row +
                ", layer=" + layer +
                ", player=" + player +
                '}';
    }
}
