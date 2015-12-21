package com.goodguys.skysafechat.listener;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
public class UserLogout {
    private String userName;

    public UserLogout(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
