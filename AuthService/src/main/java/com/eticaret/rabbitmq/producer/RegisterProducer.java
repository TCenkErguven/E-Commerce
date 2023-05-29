package com.eticaret.rabbitmq.producer;

import com.eticaret.rabbitmq.model.SaveUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProducer {
    private String exchangeName = "auth-exchange";
    private String routingOrBindingKey = "auth-routing-key-save-user";

    private final RabbitTemplate rabbitTemplate;

    public void saveNewUser(SaveUserModel saveUserModel){
        rabbitTemplate.convertAndSend(exchangeName,routingOrBindingKey,saveUserModel);
    }
}
