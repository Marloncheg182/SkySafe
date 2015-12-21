package com.goodguys.skysafechat.spring.boot.configs.autoconfig;

import com.goodguys.skysafechat.spring.boot.configs.endpoints.TraceEndpoint;
import com.goodguys.skysafechat.spring.boot.configs.trace.WebSocketTraceChannelInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.messaging.support.ExecutorSubscribableChannel;

import javax.annotation.PostConstruct;

/**
 * {@link ExecutorSubscribableChannel}
 * {@link TraceRepository}
 *
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
@Configuration
public class WebSocketAutoConfiguration {

    @Value("${management.websocket.trace-inbound:true}")
    private boolean enableTraceInboundChannel;

    @Value("${management.websocket.trace-inbound:false}")
    private boolean enableTraceOutboundChannel;

    @Autowired
    private ExecutorSubscribableChannel clientInboundChannel;

    @Autowired
    private ExecutorSubscribableChannel clientOutboundChannel;

    private TraceRepository traceRepository = new InMemoryTraceRepository();

    @Bean
    @Description("Web Socket trace")
    public TraceEndpoint traceEndpoint() {
        return new TraceEndpoint(traceRepository);
    }

    @Bean
    public WebSocketTraceChannelInterceptor webSocketTraceChannelInterceptor() {
        return new WebSocketTraceChannelInterceptor(traceRepository);
    }

    @PostConstruct
    private void addTraceInterceptor() {
        if (enableTraceInboundChannel) {
            clientInboundChannel.addInterceptor(webSocketTraceChannelInterceptor());
        }
        if (enableTraceOutboundChannel) {
            clientOutboundChannel.addInterceptor(webSocketTraceChannelInterceptor());
        }
    }

}
