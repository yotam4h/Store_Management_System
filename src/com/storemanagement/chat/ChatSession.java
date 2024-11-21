package com.storemanagement.chat;

import java.util.List;

public class ChatSession
{
    int sessionId;
    int fromUserId;
    int toUserId;
    List<String> messages;

    public ChatSession(int sessionId, int fromUserId, int toUserId, List<String> messages)
    {
        setSessionId(sessionId);
        setFromUserId(fromUserId);
        setToUserId(toUserId);
        setMessages(messages);
    }

    public int getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(int sessionId)
    {
        this.sessionId = sessionId;
    }

    public int getFromUserId()
    {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId)
    {
        this.fromUserId = fromUserId;
    }

    public int getToUserId()
    {
        return toUserId;
    }

    public void setToUserId(int toUserId)
    {
        this.toUserId = toUserId;
    }

    public List<String> getMessages()
    {
        return messages;
    }

    public void setMessages(List<String> messages)
    {
        this.messages = messages;
    }

    public void addMessage(String message)
    {
        messages.add(message);
    }

    public String toString()
    {
        return "ChatSession{" +
                "sessionId=" + sessionId +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", messages=" + messages +
                '}';
    }
}
