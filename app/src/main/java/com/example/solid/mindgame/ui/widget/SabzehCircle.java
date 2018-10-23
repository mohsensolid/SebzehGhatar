/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.solid.mindgame.R;
import com.example.solid.mindgame.Utils.PersistenceContract;

public class SabzehCircle extends FrameLayout {
    private SebzehListener mListener;
    private boolean isFill = false;
    private int currentLayer;
    private int currentRow;
    private int currentCol;
    private ImageView stateImgView;

    public void setListener(SebzehListener listener) {
        mListener = listener;
    }

    public void unFill() {
        stateImgView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.sebzeh_holder));
        stateImgView.setBackground(null);
        this.isFill = false;

    }

    public void changeItem(boolean player) {
        if (player == PersistenceContract.SebseGhatar.RED_PLAYER) {
            stateImgView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sebzeh_red));
            animaiteCell();
        } else {
            stateImgView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sebzeh_blue));
            animaiteCell();
        }
        isFill = true;
    }

    public boolean isValid(int row, int col, int layer) {
        return row == currentRow && col == currentCol && layer == currentLayer;
    }

    public interface SebzehListener {
        boolean isYourTurn();
        void cellClickedAt(int currentLayer, int currentRow, int currentCol);
    }

    public void setLayer(int layer) {
        this.currentLayer = layer;
    }

    public SabzehCircle(@NonNull Context context) {
        super(context);

    }

    public SabzehCircle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SabzehCircle);
        currentRow = ta.getInt(R.styleable.SabzehCircle_row, 0);
        currentCol = ta.getInt(R.styleable.SabzehCircle_col, 0);
        ta.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.sebzeh_item, this, true);
        }
        init();
    }

    private void init() {
        stateImgView = (ImageView) getChildAt(0);
        this.setOnClickListener(v -> {
            if (mListener != null && !isFill && mListener.isYourTurn()) {
                mListener.cellClickedAt(currentLayer, currentRow, currentCol);
            }
        });
    }

    private void animaiteCell() {
        setScaleY(0.2f);
        setScaleX(0.2f);
        animate().scaleY(1).scaleX(1).setDuration(200).start();

    }

    public SabzehCircle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
