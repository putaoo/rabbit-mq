package com.example.listener;

import com.example.config.DirectConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * Description
 * Created by putao on  2019/11/10 17:30
 **/
@Component
public class ListenerA implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(ListenerA.class);

    @Override
    @RabbitListener(queues = DirectConfig.RABBIT_QUEUE_USER)
    public void onMessage ( Message message , Channel channel ) throws Exception {
        //logger.info("消费消息：{}",new String(message.getBody()));
        System.out.println("Listener Demo 消费" + message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"));
    }
}
