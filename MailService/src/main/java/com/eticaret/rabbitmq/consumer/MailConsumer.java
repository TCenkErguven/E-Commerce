package com.eticaret.rabbitmq.consumer;

import com.eticaret.rabbitmq.model.MailUserModel;
import com.eticaret.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {
    private final MailService mailService;
    @RabbitListener(queues = ("auth-queue-send-mail"))
    public void sendActivationCode(MailUserModel mailUserModel){
        mailService.sendActivationCodeMail(mailUserModel);
    }
}
