package com.feedsexample.model;

import java.util.Date;

public class FeedsMessage {
    private int messageId;
    private String userName;
    private String textMessage;
    private Date dateOfPublish;

    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTextMessage() {
        return this.textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public void setDateOfPublish(Date dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public Date getDateOfPublish() {
        return this.dateOfPublish;
    }
}
