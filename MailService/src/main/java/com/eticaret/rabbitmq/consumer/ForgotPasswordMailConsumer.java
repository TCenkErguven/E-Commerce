package com.eticaret.rabbitmq.consumer;

import com.eticaret.rabbitmq.model.ForgotPasswordMailModel;
import com.eticaret.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordMailConsumer {
    private final MailService mailService;
    @RabbitListener(queues = ("auth-queue-forgot-mail"))
    public void forgotPasswordMail(ForgotPasswordMailModel model){
        System.out.println("Burdayım");
        System.out.println(model);
        mailService.forgotPasswordMail(model);
    }
}
