package com.goodguys.skysafechat.configurations;

import com.goodguys.skysafechat.listener.SessionListener;
import com.goodguys.skysafechat.listener.SessionRepository;
import com.goodguys.skysafechat.spring.boot.configs.endpoints.MessageMappingEndpoint;
import com.goodguys.skysafechat.spring.boot.configs.endpoints.WebSocketEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
@Configuration
public class ApplicationConfig {
    public static class DestinationPoint{
        private DestinationPoint(){}
        private static final String SESSION_LOGIN = "/sessiondata/app.login";
        private static final String SESSION_LOGOUT = "/sessiondata/app.logout";
    }

    @Bean
    @Description("Listener of user activities, while login/ logout and trace it between users")
    public SessionListener sessionEventListener(SimpMessagingTemplate messagingTemplate){
        SessionListener listener = new SessionListener(messagingTemplate, sessionRepository());
        listener.setLoginPoint(DestinationPoint.SESSION_LOGIN);
        listener.setLogoutPoint(DestinationPoint.SESSION_LOGOUT);
        return listener;
    }

    @Bean
    @Description("Stores all connected to session users ")
    public SessionRepository sessionRepository(){
        return new SessionRepository();
    }

    @Bean
    @Description("Web Socket Endpoint")
    public WebSocketEndpoint socketEndpoint(WebSocketMessageBrokerStats stats){
    return new WebSocketEndpoint(stats);
    }

    @Bean
    @Description("Web Socket message mapping endpoint")
    public MessageMappingEndpoint messageMappingEndpoint(){
        return new MessageMappingEndpoint();
    }
}
