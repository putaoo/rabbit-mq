package com.rabbit.mq.consumer.listener;


import com.rabbit.mq.consumer.config.DirectConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * Description
 * Created by putao on  2019/11/10 17:30
 **/
@Component
public class ListenerB  {

    private static final Logger logger = LoggerFactory.getLogger(ListenerB.class);

    @RabbitListener(queues = DirectConfig.RABBIT_QUEUE_USER_A)
    @RabbitHandler
    public void onMessage ( Message message , Channel channel ) throws Exception {
        //logger.info("Listener B 消费消息：{}",new String(message.getBody()));
        //System.out.println(new String(message.getBody()));
        System.out.println("Listener B 消费消息: "+ message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"));
    }
}
