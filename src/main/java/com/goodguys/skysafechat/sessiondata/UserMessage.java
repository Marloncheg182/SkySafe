package com.goodguys.skysafechat.sessiondata;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/14/2015
 */
public class UserMessage {
    private String userName;
    private String messages;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "{" +
                "userName=" + userName + ", messages=" + messages + "}";
    }
}
