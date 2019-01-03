package com.haiyu.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: RabbitConfig
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/3 19:09
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }
}
