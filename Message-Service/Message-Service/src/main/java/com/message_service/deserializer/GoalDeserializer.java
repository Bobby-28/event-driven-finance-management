package com.message_service.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.Goal;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GoalDeserializer implements Deserializer<Goal> {
    ObjectMapper objectMapper= new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Goal deserialize(String s, byte[] bytes) {
        Goal goal= null;
        try{
            goal= objectMapper.readValue(bytes, Goal.class);
        }catch (Exception e){
            throw new RuntimeException("X Failed to Deserialize Goal Message", e);
        }
        return goal;
    }

    @Override
    public void close() {
    }
}
