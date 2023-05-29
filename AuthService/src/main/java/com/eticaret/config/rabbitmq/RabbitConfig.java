package com.eticaret.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeName = "auth-exchange";
    private String queueNameSave = "auth-queue-save-user";
    private String queueNameUpdate = "user-queue-update-auth";

    private String saveRoutingOrBindingKey = "auth-routing-key-save-user";

    @Bean
    DirectExchange exchangeAuth(){return new DirectExchange(exchangeName);}
    //Register To User
    @Bean
    Queue registerQueue(){return new Queue(queueNameSave);}
    @Bean
    Queue updateQueue(){return new Queue(queueNameUpdate);}
    @Bean
    public Binding bindingRegister(final Queue registerQueue, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(registerQueue).to(exchangeAuth).with(saveRoutingOrBindingKey);
    }
    //Send Activation Mail
    private String queueNameMail = "auth-queue-send-mail";
    private String mailRoutingOrBindingKey = "auth-routing-key-send-mail";
    @Bean
    Queue mailQueue(){return new Queue(queueNameMail);}
    @Bean
    public Binding bindingMail(final Queue mailQueue,final DirectExchange exchangeAuth){
        return BindingBuilder.bind(mailQueue).to(exchangeAuth).with(mailRoutingOrBindingKey);
    }
    //Forgot Password
    private String queueForgotPasswordMail = "auth-queue-forgot-mail";
    private String forgotPasswordRoutingOrBindingKey = "auth-forgot-routing-key-send-mail";
    @Bean
    Queue queueForgotPasswordMail(){
        return new Queue(queueForgotPasswordMail);
    }
    @Bean
    public Binding bindingForgotPasswordMail(final Queue queueForgotPasswordMail,final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueForgotPasswordMail).to(exchangeAuth).with(forgotPasswordRoutingOrBindingKey);
    }


}
