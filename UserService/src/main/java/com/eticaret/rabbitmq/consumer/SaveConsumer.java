package com.eticaret.rabbitmq.consumer;

import com.eticaret.rabbitmq.model.SaveUserModel;
import com.eticaret.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveConsumer {
    private final UserProfileService userProfileService;

    @RabbitListener(queues = ("auth-queue-save-user"))
    public void saveUser(SaveUserModel model){
        log.info("User {}", model.toString());
        userProfileService.save(model);
    }
}
