package com.rabbit.mq.producer.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.nio.charset.StandardCharsets;

/**
 * Description
 * Created by putao on  2019/11/9 21:20
 **/
@Configuration
@Slf4j
public class RabbitConfig {
    /**
     *  Broker: 它提供一种传输服务，它的角色就是维护一条从生产者到消费者的路线，保证数据能够按照指定的方式进行传输。
     *  Exchange: 消息交换机，它指定消息按什么规则路由到哪个队列。
     *          FanoutExchange: 将消息分发到所有绑定的队列，没有routing_key 概念
     *          HeadersExchange: 通过添加key-value 匹配
     *          DirectExchange：按照指定的routing-key 分发到指定的队列
     *          TopicExchange: 通过关键字匹配
     *  Queue: 消息的载体，每个消息都会被投到一个或多个队列。
     *  Binging: 绑定，它的作用是把Exchange和Queue按照路由规则绑定起来。
     *  Routing Key: 路由关键字，exchange根据这个关键字进行消息投递
     *  vhost: 虚拟主机，一个broker里可以有多个vhost，用作不同用户权限的分离。
     *  Producer: 消息生产者，投递消息的程序
     *  Consumer: 消息消费者，接收消息的程序
     *  Channel: 消息通道，在客户端的每个连接里，可建立多个channel。
     *
    **/
    public static final String RABBIT_QUEUE_USER = "rabbit_queue_user";
    public static final String RABBIT_QUEUE_USER_A = "rabbit_queue_user_a";
    public static final String RABBIT_EXCHANGE_USER = "rabbit_exchange_user";
    public static final String RABBIT_ROUTING_KEY_USER = "rabbit_routing_key_user";
    public static final String RABBIT_ROUTING_KEY_USER_A = "rabbit_routing_key_user_a";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }

    @Bean(name = "generalRabbitTemplate")
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(returnCallback());
        rabbitTemplate.setConfirmCallback(confirmCallback());
        return rabbitTemplate;
    }

    @Bean RabbitTemplate.ReturnCallback returnCallback(){
        return (message, replyCode, replyText, exchange, routingKey)
              -> log.error("ReturnCallback消息发送失败: {}", new String(message.getBody(), StandardCharsets.UTF_8));
    }

    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback(){
        return (correlationData , ack ,cause) -> {
            log.info("回调id：{}",correlationData.getId());
            if ( ack ){
                log.info("消费消息成功！");
            }else {
                log.info("消费消息失败，原因：" + cause);
            }
        };
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(RabbitConfig.RABBIT_EXCHANGE_USER);
    }

    @Bean
    public Queue queueUser(){
        return new Queue(RabbitConfig.RABBIT_QUEUE_USER,true);
    }

    @Bean
    public Queue queueUserA(){
        return new Queue(RabbitConfig.RABBIT_QUEUE_USER_A,true);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queueUser()).to(directExchange()).with(RabbitConfig.RABBIT_ROUTING_KEY_USER);
    }

    @Bean
    public Binding bindingA(){
        return BindingBuilder.bind(queueUserA()).to(directExchange()).with(RabbitConfig.RABBIT_ROUTING_KEY_USER_A);
    }

}
