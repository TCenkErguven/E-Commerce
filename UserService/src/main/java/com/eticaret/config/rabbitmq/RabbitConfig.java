package com.eticaret.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private String queueNameSave = "auth-queue-save-user";
    private String queueNameUpdate = "user-queue-update-auth";

    private String exchangeName = "user-exchange";
    private String routingOrBindingKey = "user-routing-key-update-user";

    @Bean
    DirectExchange exchangeUserUpdate(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    Queue registerQueue(){return new Queue(queueNameSave);}
    @Bean
    Queue updateQueue(){return new Queue(queueNameUpdate);}

    @Bean
    public Binding bindingUpdate(final Queue updateQueue, final DirectExchange directExchange){
        return BindingBuilder.bind(updateQueue).to(directExchange).with(routingOrBindingKey);
    }

}
