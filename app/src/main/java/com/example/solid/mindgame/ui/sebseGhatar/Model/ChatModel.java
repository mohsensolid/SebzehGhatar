/*
 * Copyright (c) By Mohsen Ashouri
 */

package com.example.solid.mindgame.ui.sebseGhatar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatModel
{
    @Expose
    @SerializedName("msg")
    private String MessageText;
    @Expose
    @SerializedName("user")
    private boolean UserMessage;

    public ChatModel(String messageText, boolean userMessage) {
        MessageText = messageText;
        UserMessage = userMessage;
    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public boolean isUserMessage() {
        return UserMessage;
    }

    public void setUserMessage(boolean userMessage) {
        UserMessage = userMessage;
    }
}
