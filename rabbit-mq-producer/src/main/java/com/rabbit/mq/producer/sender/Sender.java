package com.rabbit.mq.producer.sender;

import com.rabbit.mq.producer.service.SendService;
import com.rabbit.mq.producer.utils.SpringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * Created by putao on  2019/11/9 22:58
 **/
@Component
public class Sender {
    ExecutorService executorService = Executors.newCachedThreadPool();

    @Scheduled(cron = "0 */1 * * * ?")
    public void send(){
        SendService sendService = SpringUtils.getBean(SendService.class);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for ( int i = 0 ; i < 10 ; i++ ) {
            executorService.execute(new Worker(countDownLatch,sendService));
        }
        countDownLatch.countDown();
    }
}
