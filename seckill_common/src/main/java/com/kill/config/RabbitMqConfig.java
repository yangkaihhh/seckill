package com.kill.config;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;


public class RabbitMqConfig {
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("", 5672);
        //构造方法已传入
//        connectionFactory.setHost();
//        connectionFactory.setPort();
        connectionFactory.setUsername("user");
        connectionFactory.setUsername("aaaaaa");
        connectionFactory.setVirtualHost("testHost");

        //是否开启消息确认机制
//        spring.rabbitmq.publisher-confirm在springboot2.2.0.RELEASE版本之前是amqp正式支持的属性，用来配置消息发送到交换器之后是否触发回调方法，在2.2.0及之后使用spring.rabbitmq.publisher-confirm-type属性配置代替，用来配置更多的确认类型；
//
//        其中：
//
//        NONE值是禁用发布确认模式，是默认值
//                CORRELATED值是发布消息成功到交换器后会触发回调方法
//        SIMPLE值经测试有两种效果，其一效果和CORRELATED值一样会触发回调方法，其二在发布消息成功后使用rabbitTemplate调用waitForConfirms或waitForConfirmsOrDie方法等待broker节点返回发送结果，根据返回结果来判定下一步的逻辑，要注意的点是waitForConfirmsOrDie方法如果返回false则会关闭channel，则接下来无法发送消息到broker;
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);

        return connectionFactory;
    }

    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        //设置手动确认消息消费
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }


    public RabbitTemplate rabbitTemplate(CachingConnectionFactory  factory){
        RabbitTemplate template = new RabbitTemplate(factory);
        return template;
    }
}
