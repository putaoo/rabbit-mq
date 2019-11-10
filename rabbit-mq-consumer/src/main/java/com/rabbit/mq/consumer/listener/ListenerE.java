package com.rabbit.mq.consumer.listener;

import com.rabbit.mq.consumer.config.DirectConfig;
import com.rabbit.mq.consumer.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description
 * Created by putao on  2019/11/10 16:56
 **/
@Component
@RabbitListener(queues = DirectConfig.RABBIT_QUEUE_USER)
public class ListenerE  {
    private static final Logger logger = LoggerFactory.getLogger(ListenerE.class);

    @RabbitHandler
    public void hand(Message message){
        //logger.info("Listener E 消费消息 {}",message);
        System.out.println("Listener E 消费消息 " + message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"));
    }
}
