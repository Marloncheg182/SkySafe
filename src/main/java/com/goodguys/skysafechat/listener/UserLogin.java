package com.goodguys.skysafechat.listener;

import java.util.Date;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
public class UserLogin {
    private String userName;
    private Date currentTime;

    public UserLogin(String name) {
        this.userName = name;
        currentTime = new Date();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
