package com.goodguys.skysafechat.spring.boot.configs.trace;

import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.NativeMessageHeaderAccessor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link ChannelInterceptorAdapter}
 * {@link TraceRepository}
 *
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 * @see org.springframework.messaging.simp.stomp.StompHeaderAccessor
 */

public class WebSocketTraceChannelInterceptor extends ChannelInterceptorAdapter {

    private final TraceRepository traceRepository;

    public WebSocketTraceChannelInterceptor(TraceRepository repository) {
        this.traceRepository = repository;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        Map<String, Object> trace = new LinkedHashMap<>();
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        if (headerAccessor.getCommand() == null) {
            return;
        }

        String payload = new String((byte[]) message.getPayload());
        trace.put("stompCommand", headerAccessor.getCommand());
        trace.put("nativeHeaders", getNativeHeaders(headerAccessor));
        if (!payload.isEmpty()) {
            trace.put("payload", payload);
        }
        traceRepository.add(trace);
    }

    private Map<String, Object> getNativeHeaders(StompHeaderAccessor headerAccessor) {
        Map<String, List<String>> nativeHeaders =
                (Map<String, List<String>>) headerAccessor.getHeader(NativeMessageHeaderAccessor.NATIVE_HEADERS);
        if (nativeHeaders == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, Object> traceHeaders = new LinkedHashMap<>();
        for (String header : traceHeaders.keySet()) {
            List<String> headerValue = nativeHeaders.get(header);
            Object value = headerValue;
            if (headerValue.size() == 1) {
                value = headerValue.get(0);
            } else if (headerValue.isEmpty()) {
                value = "";
            }
            traceHeaders.put(header, value);
        }
        return traceHeaders;
    }
}
