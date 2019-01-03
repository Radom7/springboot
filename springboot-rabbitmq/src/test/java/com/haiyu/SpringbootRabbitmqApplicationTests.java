package com.haiyu;

import com.haiyu.sender.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	private HelloSender helloSender1;

	@Autowired
	private HelloSender helloSender2;

	//一对一
	@Test
	public void hello() throws Exception {
		helloSender1.send(1);
	}

	//一对多
	@Test
	public void oneToMany() throws Exception {
		for (int i=0;i<100;i++){
			helloSender1.send(i);
		}
	}

	//多对多
	@Test
	public void manyToMany() throws Exception {
		for (int i=0;i<100;i++){
			helloSender1.send(i);
			helloSender2.send(i);
		}
	}

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hi, fanout msg ";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
	}

}

