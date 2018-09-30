/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.ScoreMethod;

import java.util.Objects;

public class Step {
    public final int row;
    public final int col;

    Step(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return row == step.row &&
                col == step.col;
    }

    @Override
    public int hashCode() {

        return Objects.hash(row, col);
    }
}
