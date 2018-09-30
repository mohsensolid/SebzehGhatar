/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.chat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.solid.mindgame.R;
import com.example.solid.mindgame.ui.sebseGhatar.Model.ChatModel;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconTextView;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatModel> items;

    public ChatAdapter(List<ChatModel> items) {
        this.items = items;
    }

    public void setData(List<ChatModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItem view = (ChatItem) LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatModel model = items.get(position);
        ChatHolder chatHolder = (ChatHolder) holder;
        chatHolder.bind(model);
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addNewMessage(ChatModel item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public class ChatHolder extends RecyclerView.ViewHolder{
        public final ChatItem view;

        public ChatHolder(ChatItem itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view = itemView;
        }

        public void bind(ChatModel model) {
            view.bindTo(model);
        }
    }
}
