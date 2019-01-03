package com.haiyu.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title: HelloReceiver
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/3 19:12
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}
