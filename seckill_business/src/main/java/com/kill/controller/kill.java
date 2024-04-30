package com.kill.controller;


import com.kill.config.RabbitMqConfig;
import com.kill.utils.RabbitMqUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/test")
public class kill {

   @Resource
   private RabbitMqUtil rabbitMqUtil;

    @Resource
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("/test")
    public void test(){
        for ( int i = 0; i < 10; i++) {
            rabbitMqUtil.sendMessage("hello-->" + i);
        }
    }

    //接收消息
//    @RabbitListener(queues = {RabbitMqConfig.QUEUE_INFORM_EMAIL})
//    public void receiveMessage(Message message){
//        String messageBody = new String(message.getBody());
//        // 模拟异常
//        System.out.println(1 / 0);
//        System.out.println("-------->" + messageBody);
//    }

    @RabbitListener(queues = {"error.queue"})
    public void errorMessage(Message message){
        String messageBody = new String(message.getBody());

        System.out.println("-------->" + messageBody);
    }


    @RequestMapping("/test1")
    public void  test1(){
        // 模拟发送十万条数据
        long b = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            // 1.准备消息
            Message message = MessageBuilder
                    .withBody("hello, LazyQueue".getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT) // 改成非持久化，可以看一下LazyQueue的效果
                    .build();
            // 2.发送消息
            rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_INFORM_LAZY, message);
        }
        long e = System.nanoTime();
        System.out.println(e - b);
    }
    @RequestMapping("/test2")
    public void test2(){
        // 模拟发送十万条数据
        long b = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            // 1.准备消息
            Message message = MessageBuilder
                    .withBody("hello, Spring".getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT) // 改成非持久化，可以看一下正常队列的效果
                    .build();
            // 2.发送消息
            rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_INFORM_EMAIL, message);
        }
        long e = System.nanoTime();
        System.out.println(e - b);
            }



}
