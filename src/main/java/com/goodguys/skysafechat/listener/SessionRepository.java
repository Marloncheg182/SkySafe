package com.goodguys.skysafechat.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
public class SessionRepository {
    private Map<String, UserLogin> openSessions = new ConcurrentHashMap<>();
    public void add(String sessionId, UserLogin authorization){
        openSessions.put(sessionId, authorization);
    }
    public UserLogin getSessionUser(String userId){
         return openSessions.get(userId);
    }
    public Map<String, UserLogin> getOpenSessions(){
        return openSessions;
    }

    public void removeUserFromSession(String userId){
        openSessions.remove(userId);
    }
    public void setOpenSessions(Map<String, UserLogin> sessions){
        this.openSessions = sessions;
    }
}
