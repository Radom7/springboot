package com.haiyu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Title: KafkaProducerController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/28 15:33
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("send")
    public String send(String msg){
        logger.info("生产者生产的消息："+msg);
        kafkaTemplate.send("test_topic", msg);
        return "success";
    }

}
