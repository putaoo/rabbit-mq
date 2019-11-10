package com.rabbit.mq.producer.service.impl;

import com.rabbit.mq.producer.config.RabbitConfig;
import com.rabbit.mq.producer.producer.GeneralProducer;
import com.rabbit.mq.producer.producer.Producer;
import com.rabbit.mq.producer.service.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description
 * Created by putao on  2019/11/9 22:20
 **/
@Service
public class SendServiceImpl implements SendService {
    private static final Logger logger = LoggerFactory.getLogger(SendServiceImpl.class);

    @Autowired
    private Producer producer;

    @Autowired
    private GeneralProducer generalProducer;

    @Override
    public void send ( String exchange , String routingKey , String content ) {
        if ( routingKey == RabbitConfig.RABBIT_ROUTING_KEY_USER_A ){
            logger.info("奇数：常规的生产者：====================");
            generalProducer.send(exchange,routingKey,content);
        }else {
            logger.info("偶数：定制的生产者：====================");
            producer.send(exchange,routingKey,content);
        }

    }
}
