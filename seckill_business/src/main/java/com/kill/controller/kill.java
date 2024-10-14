package com.kill.controller;


import com.kill.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class kill {

   @Resource
   private RabbitMqUtil rabbitMqUtil;

    @RequestMapping("/test")
    public void test(){
        for ( int i = 0; i < 10; i++) {
            rabbitMqUtil.sendMessage("yk.exchange","yk.key" , "你好"+ i );
        }
    }


    @RabbitListener(queues = "yk.queue")
    public void test2(String msg , @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag , Channel channel) throws IOException {

        System.out.println("标签-" + deliveryTag);
        System.out.println("接收到消息：" + msg);
        if( msg.equals("你好1")){
            channel.basicNack(deliveryTag , false , false);
        }else
        {
            channel.basicAck(deliveryTag, false);
        }
    }

    @RabbitListener(queues = "yk.dlx.queue.a")
    public void test3(String msg , @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag , Channel channel) throws IOException {

        System.out.println("死信-标签-" + deliveryTag);
        System.out.println("死信-接收到消息：" + msg);
        channel.basicAck(deliveryTag, false);
    }

}
