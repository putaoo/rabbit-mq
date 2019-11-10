package com.rabbit.mq.producer.service;

/**
 * Description
 * Created by putao on  2019/11/9 22:19
 **/
public interface SendService {

    void send(String exchange,String routingKey,String content);
}
