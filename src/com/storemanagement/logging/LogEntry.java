package com.storemanagement.logging;

import com.storemanagement.utils.Constants.OperationType;

import java.time.LocalDateTime;

public class LogEntry
{
    int logId;
    LocalDateTime timestamp;
    OperationType operationType;
    String details;

    public LogEntry(int logId, LocalDateTime timestamp, OperationType operationType, String details)
    {
        this.logId = logId;
        this.timestamp = timestamp;
        this.operationType = operationType;
        this.details = details;
    }

    public int getLogId()
    {
        return logId;
    }

    public void setLogId(int logId)
    {
        this.logId = logId;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public OperationType getOperationType()
    {
        return operationType;
    }

    public void setOperationType(OperationType operationType)
    {
        this.operationType = operationType;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    @Override
    public String toString()
    {
        return "LogEntry{" +
                "logId=" + logId +
                ", timestamp=" + timestamp +
                ", operationType=" + operationType +
                ", details='" + details + '\'' +
                '}';
    }
}
