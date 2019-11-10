package com.rabbit.mq.producer.producer;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Description  定制化的发送者，可以配置不同的消息确认于回调机制
 * Created by putao on  2019/11/9 21:19
 **/
@Component
public class GeneralProducer{

    private static final Logger logger = LoggerFactory.getLogger(GeneralProducer.class);

    @Autowired
    private RabbitTemplate generalRabbitTemplate;

    public void send(String exchange,String routingKey,String content){
        logger.info("发送消息：{},Exchange:{},RoutingKey :{}",content,exchange,routingKey);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replace("-",""));
        logger.info("CorrelationData: {}", JSONObject.toJSONString(correlationData));
        generalRabbitTemplate.convertAndSend(exchange,routingKey,content,correlationData);
    }

    /**
    * publisher-confirms: true 消息有没有到达MQ（会返回一个ack确认码）
    * @author putao
    * @date  2019/11/10 14:32
    **/


    /**
    * publisher-returns: true 消息有没有找到合适的队列,主要是为了生产者和mq之间的一个确认机制，当消息到没到mq，会提供相应的回调，在项目中 RabbitSender 这个类中进行了相应的配置
    * @author putao
    * @date  2019/11/10 14:32
    **/

}
