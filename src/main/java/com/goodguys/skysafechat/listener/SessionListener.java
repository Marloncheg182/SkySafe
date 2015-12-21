package com.goodguys.skysafechat.listener;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

/**
 * This class supports the validation,
 * to determine users in active session.
 * Listener messages will be sent to handlers,
 * when login or logout point will be reached
 * in validation form.
 *
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
public class SessionListener {
    private SessionRepository sessionRepository;
    private SimpMessagingTemplate messagingTemplate;
    private String loginPoint;
    private String logoutPoint;

    public SessionListener(SimpMessagingTemplate messagingTemplate, SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        this.messagingTemplate = messagingTemplate;
    }
//    @EventListener
//    private void handleConnectToSession(SessionConnectEvent event){
//        SimpMessageHeaderAccessor messageHeaders = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String user = messageHeaders.getUser().getName();
//
//        UserLogin login = new UserLogin(user);
//        messagingTemplate.convertAndSend(loginPoint, login);
//
//        sessionRepository.add(messageHeaders.getSessionId(), login);
//    }

//    @EventListener
//    private void handleDosconnectFromSession(SessionDisconnectEvent event) {
//        Optional.ofNullable(sessionRepository
//                .getSessionUser(event.getSessionId()))
//                .ifPresent(userLogin -> {
//                    messagingTemplate.convertAndSend(logoutPoint,
//                    new UserLogout(userLogin.getUserName()));
//                    sessionRepository.removeUserFromSession(event.getSessionId());
//                });
//    }


    public void setLoginPoint(String loginPoint) {
        this.loginPoint = loginPoint;
    }

    public void setLogoutPoint(String logoutPoint) {
        this.logoutPoint = logoutPoint;
    }
}
