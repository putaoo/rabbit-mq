package com.rabbit.mq.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 * Created by putao on  2019/11/10 17:55
 **/
@Configuration
public class DirectConfig  {
    public static final String RABBIT_QUEUE_USER_A = "rabbit_queue_user_a";
    public static final String RABBIT_QUEUE_USER = "rabbit_queue_user";

    @Bean
    public Queue queueA(){
        return new Queue(RABBIT_QUEUE_USER_A);
    }
    @Bean
    public Queue queue(){
        return new Queue(RABBIT_QUEUE_USER);
    }

    /**
    *
     spring.rabbitmq.listener.type=direct
     spring.rabbitmq.listener.direct.acknowledge-mode=manual
     spring.rabbitmq.listener.direct.consumers-per-queue=2
     spring.rabbitmq.listener.direct.prefetch=2
     spring.rabbitmq.listener.direct.missing-queues-fatal=true
     spring.rabbitmq.listener.direct.default-requeue-rejected=false
     spring.rabbitmq.listener.direct.auto-startup=false
     spring.rabbitmq.listener.simple.acknowledge-mode=manual
     spring.rabbitmq.listener.simple.prefetch=2
     spring.rabbitmq.listener.simple.concurrency=5
     spring.rabbitmq.listener.simple.max-concurrency=10
     spring.rabbitmq.listener.simple.missing-queues-fatal=true
     spring.rabbitmq.listener.simple.default-requeue-rejected=false
     spring.rabbitmq.listener.simple.auto-startup=false
     spring.rabbitmq.listener.simple.batch-size=5
    * @author putao
    * @date  2019/11/10 18:06
    **/
}
