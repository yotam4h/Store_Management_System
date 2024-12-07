package com.storemanagement.chat;

import java.sql.Timestamp;
import java.util.List;

public class ChatSession {
    private int id;
    private int fromUserId;
    private int toUserId;
    private Timestamp startTime;
    private Timestamp endTime;

    // Constructor for a new session
    public ChatSession(int fromUserId, int toUserId, Timestamp startTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.startTime = startTime;
        this.endTime = null; // Initial session has no end time
    }

    // Constructor for a completed session
    public ChatSession(int fromUserId, int toUserId, Timestamp startTime, Timestamp endTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    // Method to retrieve the chat history (all messages in the session)
    public List<ChatMessage> getChatHistory(ChatService chatService) {
        return chatService.getChatHistory(this.id);
    }

    @Override
    public String toString() {
        return "ChatSession{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
