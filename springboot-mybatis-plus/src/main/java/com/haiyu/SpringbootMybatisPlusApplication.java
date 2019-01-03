package com.haiyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootMybatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisPlusApplication.class, args);
	}

}

