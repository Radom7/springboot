package com.haiyu.activeMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/22.
 */
@Component
public class JMSConsumer2 {
    private final static Logger logger = LoggerFactory.getLogger(JMSConsumer2.class);

    @JmsListener(destination = "springboot.queue.test")
    @SendTo("out.queue")
    public void receiveQueue(String msg) {
        logger.info("接收到消息：{}",msg);
    }
}
