package com.kill.utils;

import com.kill.CallBack;
import com.kill.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.plugin2.jvm.RemoteJVMLauncher;

import javax.annotation.Resource;
import java.util.Base64;

@Component
public class RabbitMqUtil {
    @Resource
    private RabbitTemplate rabbitTemplate;

    //发送消息
    public void sendMessage(String message){

        //参数介绍： 交换机名字，路由建， 消息内容
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TOPICS_INFORM, "inform.email", message);

    }
}
