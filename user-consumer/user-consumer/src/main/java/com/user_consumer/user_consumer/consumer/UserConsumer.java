package com.user_consumer.user_consumer.consumer;

import com.user_consumer.user_consumer.entities.User;
import com.user_consumer.user_consumer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    Logger logger= LoggerFactory.getLogger(UserConsumer.class);
    @Autowired
    UserRepository userRepository;

    @KafkaListener(topics = "user-signup-topic", groupId = "user-consumer-group")
    public void listen(User user){
        try {
            logger.info("User Date {}", user);
            userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
