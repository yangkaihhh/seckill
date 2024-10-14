package com.kill.config;


import com.kill.CallBack;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    private static final String DLX_EXCHANGE = "yk.dlx.exchange";
    private static final String DLX_QUEUE_A = "yk.dlx.queue.a";
    private static final String DLX_ROUTING_KEY_A = "yk.dlx.key.a";

    private static final String YK_QUEUE = "yk.queue";
    private static final String YK_EXCHANGE = "yk.exchange";
    private static final String YK_ROUTING_KEY = "yk.key";

    // 声明业务Exchange
    @Bean("businessExchange")
    public TopicExchange businessExchange(){
        return new TopicExchange(YK_EXCHANGE);
    }

    // 声明死信Exchange
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DLX_EXCHANGE);
    }
    // 声明业务队列A
    @Bean("businessQueueA")
    public Queue businessQueueA(){
        Map<String, Object> args = new HashMap<>(2);
        //       x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DLX_EXCHANGE);
        //       x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY_A);
        return QueueBuilder.durable(YK_QUEUE).withArguments(args).build();
    }

    // 声明死信队列A
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA(){
        return new Queue(DLX_QUEUE_A , true ,true ,false);
    }

    // 声明业务队列A绑定关系
    @Bean
    public Binding businessBindingA(@Qualifier("businessQueueA") Queue queue,
                                    @Qualifier("businessExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(YK_ROUTING_KEY);
    }


    // 声明死信队列A绑定关系  死信队列绑定死信交换机 死信队列绑定死信路由key
    @Bean
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue queue,
                                      @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DLX_ROUTING_KEY_A);
    }
    /**********************************消费者消费失败后发送到队列*************************************************/
    @Bean
    public DirectExchange errorMessageExchange(){
        return new DirectExchange("yk.error.exchange");
    }
    @Bean
    public Queue errorQueue(){
        return new Queue("yk.error.queue", true);
    }
    @Bean
    public Binding errorBinding(Queue errorQueue, DirectExchange errorMessageExchange){
        return BindingBuilder.bind(errorQueue).to(errorMessageExchange).with("yk.error.key");
    }

    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate, "yk.error.exchange", "yk.error.key");
    }

}
