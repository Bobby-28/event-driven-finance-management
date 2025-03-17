package com.user_consumer.user_consumer.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_consumer.user_consumer.entities.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserConsumerDeserializer implements Deserializer<User> {
    ObjectMapper objectMapper= new ObjectMapper();
    //    or user this
//    @AutoWired
//    ObjectMapper objectMapper

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public User deserialize(String s, byte[] bytes) {
        User user= null;
        try {
            user= objectMapper.readValue(bytes, User.class);
        }catch (Exception e){
            throw new RuntimeException("❌ Failed to deserialize message", e);
        }
        return user;
    }

    @Override
    public void close() {
    }
}
