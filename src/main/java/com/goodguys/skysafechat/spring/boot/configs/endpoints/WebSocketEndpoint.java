package com.goodguys.skysafechat.spring.boot.configs.endpoints;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

/**
 * Web Socket Endpoint configuration
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 * {@link org.springframework.boot.actuate.endpoint.Endpoint}
 * {@link AbstractEndpoint}
 */
@ConfigurationProperties(prefix = "endpoints.websocketendpoint", ignoreUnknownFields = true)
public class WebSocketEndpoint extends AbstractEndpoint<WebSocketMessageBrokerStats> {

    private WebSocketMessageBrokerStats messageBrokerStats;

    public WebSocketEndpoint(WebSocketMessageBrokerStats webSocketMessageBrokerStats) {
        super("websocketstatus");
        this.messageBrokerStats = webSocketMessageBrokerStats;
    }

    @Override
    public WebSocketMessageBrokerStats invoke() {
        return messageBrokerStats;
    }
}
