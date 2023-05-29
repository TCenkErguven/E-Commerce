package com.eticaret.rabbitmq.producer;

import com.eticaret.rabbitmq.model.ForgotPasswordMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordMailProducer {

    private String exchangeName = "auth-exchange";
    private String forgotPasswordRoutingOrBindingKey = "auth-forgot-routing-key-send-mail";

    private final RabbitTemplate rabbitTemplate;

    public void forgotPasswordMail(ForgotPasswordMailModel model){
        rabbitTemplate.convertAndSend(exchangeName,forgotPasswordRoutingOrBindingKey,model);
    }
}
