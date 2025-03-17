package com.message_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer{
    @Autowired
    JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper= new ObjectMapper();
    String from= "adityabedi3000@gmail.com";
    @KafkaListener(topics = "user-signup-topic", groupId = "${spring.kafka.consumer-groups.user-event-group.group-id}")
    public void listen(String message){
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        try{
            User user= objectMapper.readValue(message, User.class);
            String to= user.getEmail();
            String subject= "Finance-Management-Application  [User-Creation]";
            String emessage= "User Created";
            simpleMailMessage.setTo(to);
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(emessage);
            javaMailSender.send(simpleMailMessage);
            // for testing purpose
            System.out.println("User Created!!!!!!");
            System.out.println("User Id : " + user.getUserId());
            System.out.println("User Name : " + user.getUsername());
            System.out.println("User Email : " + user.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
