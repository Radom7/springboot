package com.haiyu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class SpringbootSolrApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSolrApplication.class, args);
	}

	@Autowired
	private RestTemplateBuilder builder;

	@Autowired
	private RestTemplate restTemplate;

	// 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}


	//每五秒执行一次
	@Scheduled(cron = "0/5 * * * * *")
	public void updateSolr() {
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("command", "full-import");
		postParameters.add("verbose", "false");
		postParameters.add("clean", "true");
		postParameters.add("commit", "true");
		postParameters.add("core", "ik_core");
		postParameters.add("name", "dataimport");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity <MultiValueMap <String, Object>> r = new HttpEntity<>(postParameters, headers);
		String time = String.valueOf(new Date().getTime());
		String url = "http://192.168.0.197:8983/solr/ik_core/dataimport?_=" + time + "&indent=on&wt=json";
		String responseMessage = restTemplate.postForObject(url, r, String.class);

		logger.info("更新solr索引：返回值：{}", responseMessage);
	}

}

