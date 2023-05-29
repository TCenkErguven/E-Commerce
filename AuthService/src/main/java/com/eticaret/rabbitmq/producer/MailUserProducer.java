package com.eticaret.rabbitmq.producer;

import com.eticaret.rabbitmq.model.MailUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailUserProducer {
    private String exchangeName = "auth-exchange";
    private String mailRoutingOrBindingKey = "auth-routing-key-send-mail";
    private final RabbitTemplate rabbitTemplate;

    public void sendActivationCode(MailUserModel mailUserModel){
        rabbitTemplate.convertAndSend(exchangeName,mailRoutingOrBindingKey,mailUserModel);
    }

}
