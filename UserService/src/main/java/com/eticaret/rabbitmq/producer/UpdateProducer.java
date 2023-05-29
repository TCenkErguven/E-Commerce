package com.eticaret.rabbitmq.producer;

import com.eticaret.rabbitmq.model.UpdateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProducer {
    private String exchangeName = "user-exchange";
    private String routingOrBindingKey = "user-routing-key-update-user";

    private final RabbitTemplate rabbitTemplate;

    public void updateUser(UpdateUserModel model){
        rabbitTemplate.convertAndSend(exchangeName,routingOrBindingKey,model);
    }
}
