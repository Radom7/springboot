package com.haiyu;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/** * @Title: TestConsumer
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/9/7 11:07
 */
@Component
public class TestConsumer {

    @KafkaListener(topics = "test_topic")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }
}
