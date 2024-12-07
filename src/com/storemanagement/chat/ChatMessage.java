package com.storemanagement.chat;

import java.sql.Timestamp;

public class ChatMessage {
    private int id;
    private int senderId;
    private int chatSessionId;
    private String messageText;
    private Timestamp timestamp;

    // Parameterized Constructor
    public ChatMessage(int id, int senderId, int chatSessionId, String messageText, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.chatSessionId = chatSessionId;
        this.messageText = messageText;
        this.timestamp = timestamp;
    }

    // Overloaded Constructor without ID (for new messages)
    public ChatMessage(int senderId, int chatSessionId, String messageText, Timestamp timestamp) {
        this.senderId = senderId;
        this.chatSessionId = chatSessionId;
        this.messageText = messageText;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getChatSessionId() {
        return chatSessionId;
    }

    public void setChatSessionId(int chatSessionId) {
        this.chatSessionId = chatSessionId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", chatSessionId=" + chatSessionId +
                ", messageText='" + messageText + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
