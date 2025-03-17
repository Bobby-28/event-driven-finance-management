package com.user_service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_service.entities.User;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User> {
    ObjectMapper objectMapper= new ObjectMapper();
//    or user this
//    @AutoWired
//    ObjectMapper objectMapper
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, User user) {
        byte[] retVal= null;
        try{
            retVal= objectMapper.writeValueAsString(user).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {
    }
}
