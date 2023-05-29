package com.eticaret.rabbitmq.consumer;

import com.eticaret.rabbitmq.model.UpdateUserModel;
import com.eticaret.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateConsumer {
    private final AuthService authService;
    @RabbitListener(queues = ("user-queue-update-auth"))
    public void updateUser(UpdateUserModel model) {
        System.out.println(model);
        authService.updateUserInfo(model);
    }
}
