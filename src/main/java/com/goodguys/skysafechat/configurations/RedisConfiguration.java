package com.goodguys.skysafechat.configurations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
@Configuration
public class RedisConfiguration {
    public static RedisServerBean getServer(){
        return new RedisServerBean();
    }

    static class RedisServerBean implements InitializingBean, DisposableBean, BeanDefinitionRegistryPostProcessor {
       private RedisServer redisServer;
        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        }

        @Override
        public void destroy() throws Exception {

            if(redisServer != null){
                redisServer.stop();
            }
        }

        @Override
        public void afterPropertiesSet() throws Exception {

          //TODO Must be SSH protocol implemented
          //  redisServer = new RedisServer(Protocol.DEFAULT_PORT);
          //  redisServer.start();
        }
    }
}
