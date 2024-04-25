package com.kill.utils;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMqUtil {
    @Resource
    private RabbitTemplate rabbitTemplate;

    //发送消息
    public void sendMessage(String message){
        //参数介绍： 交换机名字，路由建， 消息内容
        rabbitTemplate.convertAndSend("exchangeName" , "dir.key" , "hello world");
    }

    //接收消息
    @RabbitListener(queues = "queueName")
    public void receiveMessage(String message){
        System.out.println("-------->" + message);
    }
}
