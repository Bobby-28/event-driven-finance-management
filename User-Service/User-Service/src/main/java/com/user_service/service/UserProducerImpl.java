package com.user_service.service;

import com.user_service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserProducerImpl implements UserProducer{

    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public UserProducerImpl(KafkaTemplate<String, User> kafkaTemplate){
        this.kafkaTemplate= kafkaTemplate;
    }
    @Override
    public void sendUserData(User user) {
        Message<User> message= MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "user-signup-topic").build();
        kafkaTemplate.send(message);
    }
}
