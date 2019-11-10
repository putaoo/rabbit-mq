package com.rabbit.mq.consumer.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.mq.consumer.config.DirectConfig;
import com.rabbit.mq.consumer.config.RabbitConfig;
import com.rabbit.mq.consumer.entity.User;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description
 * Created by putao on  2019/11/9 23:08
 **/
@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = DirectConfig.RABBIT_QUEUE_USER_A)
    public void listen( Message message, Channel channel ) throws IOException{
        //手动ack
        //long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //channel.basicAck(deliveryTag,true);

        //logger.info("Listener 消费消息{}", JSONObject.parseObject(new String(message.getBody()), User.class));
        System.out.println("Listener 消费消息: "+ message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"));
    }
}
