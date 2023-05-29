package com.eticaret.service;

import com.eticaret.rabbitmq.model.ForgotPasswordMailModel;
import com.eticaret.rabbitmq.model.MailUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendActivationCodeMail(MailUserModel model) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Aktivasyon Kodu");
        mailMessage.setFrom("erguven.cenk@gmail.com");
        mailMessage.setTo(model.getEmail());
        mailMessage.setText("Dear" + " " + model.getUsername() + " \nYour Activation Code is:" + model.getActivationCode());
        javaMailSender.send(mailMessage);
    }

    public void forgotPasswordMail(ForgotPasswordMailModel model) {
        try {
           SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("Forgot Password");
            mailMessage.setFrom("${spring.mail.username}");
            mailMessage.setTo(model.getEmail());
            mailMessage.setText("Dear" + " " + model.getUsername() +
                    "\nYour new password is: " + model.getPassword());
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            e.getMessage();
        }
    }
}
