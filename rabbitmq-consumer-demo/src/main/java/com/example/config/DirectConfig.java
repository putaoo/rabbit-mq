package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 * Created by putao on  2019/11/10 17:55
 **/
@Configuration
public class DirectConfig {
    public static final String RABBIT_QUEUE_USER = "rabbit_queue_user_a";

    @Bean
    public Queue queue(){
        return new Queue(RABBIT_QUEUE_USER);
    }
}
