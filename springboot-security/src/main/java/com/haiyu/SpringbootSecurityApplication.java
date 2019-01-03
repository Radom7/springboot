package com.haiyu;

import com.haiyu.config.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class SpringbootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSecurityApplication.class, args);

		String[] controllers = SpringUtil.controllers(Controller.class);
		if(controllers != null) {
			for (String controllerBeanName : controllers) {
				System.out.println(controllerBeanName);
			}
		}
		System.out.println("Controller的数量:"+controllers.length);
	}

}

