package com.goal_service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goal_service.enitities.Goal;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class GoalSerializer implements Serializer<Goal> {

    ObjectMapper objectMapper= new ObjectMapper();
//    @Autowired
//    ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, Goal goal) {
        byte[] retVal= null;
        try{
            retVal= objectMapper.writeValueAsString(goal).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {
    }
}
