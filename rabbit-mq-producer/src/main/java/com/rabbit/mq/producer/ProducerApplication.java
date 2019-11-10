package com.rabbit.mq.producer;

import com.rabbit.mq.producer.utils.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ProducerApplication.class , args);
		SpringUtils.setApplicationContext(applicationContext);
	}
}
