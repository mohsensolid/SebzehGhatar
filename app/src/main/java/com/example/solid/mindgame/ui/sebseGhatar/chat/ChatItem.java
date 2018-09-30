/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.chat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.solid.mindgame.R;
import com.example.solid.mindgame.ui.sebseGhatar.Model.ChatModel;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.rockerhieu.emojicon.EmojiconTextView;

public class ChatItem extends RelativeLayout {
    @BindView(R.id.messageTxt)
    EmojiconTextView msg_content;
    @BindDrawable(R.drawable.balloon_hangout_block_incoming)
    Drawable incomingMsg;
    @BindDrawable(R.drawable.balloon_hangout_block_outgoing)
    Drawable outGoingMeg;
    @BindView(R.id.bubbuleHolder)
    LinearLayout bubbuleHolder;

    public ChatItem(Context context) {
        super(context, null);
    }

    public ChatItem(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ChatItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(ChatModel model) {
        if (model.isUserMessage()) {
            bubbuleHolder.setBackground(incomingMsg);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
            } else {
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            bubbuleHolder.setLayoutParams(params);
        } else {
            bubbuleHolder.setBackground(outGoingMeg);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                params.addRule(RelativeLayout.ALIGN_START);
            } else {
                params.addRule(RelativeLayout.ALIGN_LEFT);
            }
            bubbuleHolder.setLayoutParams(params);
        }
        msg_content.setText(model.getMessageText());
    }
}
