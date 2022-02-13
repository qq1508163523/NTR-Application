package com.ntr.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    private final JavaMailSenderImpl mailSender;

    public EmailUtil(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendVerificationCode(String to,String verificationCode){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("NTR registration code");
        simpleMailMessage.setText("Hi, your verification code is "+verificationCode);
        mailSender.send(simpleMailMessage);
    }
}
