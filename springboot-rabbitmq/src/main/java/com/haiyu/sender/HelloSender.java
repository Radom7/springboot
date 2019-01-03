package com.haiyu.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Title: HelloSender
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/3 19:11
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "hello " + new Date()+"***********"+i;
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}
