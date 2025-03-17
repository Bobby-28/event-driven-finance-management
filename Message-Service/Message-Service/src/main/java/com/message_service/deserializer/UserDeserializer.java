package com.message_service.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserDeserializer implements Deserializer<User> {
    ObjectMapper objectMapper= new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public User deserialize(String s, byte[] bytes) {
        User user= null;
        try{
            user= objectMapper.readValue(bytes, User.class);
        }catch (Exception e){
            throw new RuntimeException("X Failed to Deserializer User Message", e);
        }
        return user;
    }

    @Override
    public void close() {
    }
}
