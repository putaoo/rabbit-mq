package com.rabbit.mq.producer.utils;

import org.springframework.context.ApplicationContext;

/**
 * Description
 * Created by putao on  2019/11/9 22:25
 **/
public class SpringUtils {

    private static ApplicationContext applicationContext = null;


    public static void setApplicationContext(ApplicationContext applicationContext){
        if ( null ==  SpringUtils.applicationContext){
            SpringUtils.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }
}
