/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongcopper80.springboot.mongo.example.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author dongc
 */
@Component
public class SendMail {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Scheduled(fixedRate = 24 * 60 * 60000)
    public void reportCurrentTime() {
        sendEmail("Start send mail " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                "Schedule auto send mail at " + dateFormat.format(new Date()));
    }
    
    private void sendEmail(String title, String content) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("abc@gmail.com");
        msg.setSubject(title);
        msg.setText(content);

        javaMailSender.send(msg);
    }
}
