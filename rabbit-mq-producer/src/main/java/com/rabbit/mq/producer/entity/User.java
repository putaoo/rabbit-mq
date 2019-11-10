package com.rabbit.mq.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Description
 * Created by putao on  2019/11/9 21:17
 **/
@Data
@AllArgsConstructor
public class User implements Serializable {

    private String name;
    private int age;
    private String phone;
}
