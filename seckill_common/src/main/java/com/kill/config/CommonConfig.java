package com.kill.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //获取RabbitTemplate
        RabbitTemplate template = applicationContext.getBean(RabbitTemplate.class);

        //设置ReturnCallback
        // 设置 ConfirmCallback
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
            } else {
                System.out.println("消息到交换机发送失败: " + cause);
            }
        });
        template.setReturnsCallback((returnedMessage) ->{
            System.out.println("消息到队列发送失败: " + returnedMessage.getMessage());
        });
    }
}
