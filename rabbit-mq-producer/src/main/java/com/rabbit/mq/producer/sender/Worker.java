package com.rabbit.mq.producer.sender;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.mq.producer.config.RabbitConfig;
import com.rabbit.mq.producer.entity.User;
import com.rabbit.mq.producer.service.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Description
 * Created by putao on  2019/11/9 23:55
 **/
public class Worker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    private CountDownLatch countDownLatch;
    private SendService sendService;

    public Worker ( CountDownLatch countDownLatch , SendService sendService ) {
        this.countDownLatch = countDownLatch;
        this.sendService = sendService;
    }

    @Override
    public void run () {
        try {
            countDownLatch.await();
            for ( int i = 0 ; i < 100 ; i++ ) {
                if ( i % 2 == 0 ){
                    User user = new User( i +"" ,i,"1350000000"+i);
                    String s = JSONObject.toJSONString(user);
                    sendService.send(RabbitConfig.RABBIT_EXCHANGE_USER,RabbitConfig.RABBIT_ROUTING_KEY_USER,s);
                }else {
                    User user = new User(i +""  ,i,"1350000000"+i);
                    String s = JSONObject.toJSONString(user);
                    sendService.send(RabbitConfig.RABBIT_EXCHANGE_USER,RabbitConfig.RABBIT_ROUTING_KEY_USER_A,s);
                }
            }
        }catch ( Exception e ){
            logger.info("发送出错{}",e.getMessage());
        }
    }
}
