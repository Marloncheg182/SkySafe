package com.goodguys.skysafechat.spring.boot.configs.endpoints;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.messaging.simp.SimpMessageMappingInfo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Message Mapping configuration
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 * {@link AbstractEndpoint}
 * {@link ApplicationContextAware}
 */
public class MessageMappingEndpoint extends AbstractEndpoint implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public MessageMappingEndpoint() {
        super("websocketmapping");
    }


    @Override
    public Map<String, Object> invoke() {
        SimpAnnotationMethodMessageHandler handler = applicationContext
                .getBean(SimpAnnotationMethodMessageHandler.class);
        Map<SimpMessageMappingInfo, HandlerMethod> handlerMethod =
                this.filterByAnnotation(handler.getHandlerMethods(), MessageMapping.class);
        Map<SimpMessageMappingInfo, HandlerMethod> subscribeMethod =
                this.filterByAnnotation(handler.getHandlerMethods(), SubscribeMapping.class);
        Map<String, Object> info = new HashMap<>();
        info.put("prefixes", handler.getDestinationPrefixes());
        info.put("messageMapping", extract(handlerMethod));
        info.put("subscribeMappings", extract(subscribeMethod));
        return info;
    }

    private Map<String, Object> extract(Map<SimpMessageMappingInfo, HandlerMethod> handlerMethods) {
        Map<String, Object> mappings = new HashMap<>();

        for (SimpMessageMappingInfo info : handlerMethods.keySet()) {
            HandlerMethod handlerMethod = handlerMethods.get(info);

            Map<String, String> store = new LinkedHashMap<>();
            store.put("bean", handlerMethod.getBean().toString());
            store.put("method", handlerMethod.toString());

            mappings.put(info.getDestinationConditions().toString(), store);
        }
        return mappings;
    }

    private Map<SimpMessageMappingInfo, HandlerMethod> filterByAnnotation(
            Map<SimpMessageMappingInfo, HandlerMethod> handlerMethods,
            Class<? extends Annotation> annotation) {
        Map<SimpMessageMappingInfo, HandlerMethod> filteredHandlerMethods =
                new LinkedHashMap<>();
        for (SimpMessageMappingInfo info : handlerMethods.keySet()) {
            HandlerMethod handlerMethod = handlerMethods.get(info);
            if (handlerMethod.getMethodAnnotation(annotation) != null) {
                filteredHandlerMethods.put(info, handlerMethod);
            }
        }
        return filteredHandlerMethods;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
