package com.eticaret.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    private String queueNameMail = "auth-queue-send-mail";
    @Bean
    Queue mailQueue(){return new Queue(queueNameMail);}

    private String queueForgotPasswordMail = "auth-queue-forgot-mail";
    @Bean
    Queue queueForgotPasswordMail(){
        return new Queue(queueForgotPasswordMail);
    }
}
