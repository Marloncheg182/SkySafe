package com.goodguys.skysafechat.web;

import com.goodguys.skysafechat.spring.boot.configs.autoconfig.WebSocketAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Oleg Romanenchuk
 * @version 1.0.0 from 12/21/2015
 */
@SpringBootApplication
@Import(WebSocketAutoConfiguration.class)
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}
