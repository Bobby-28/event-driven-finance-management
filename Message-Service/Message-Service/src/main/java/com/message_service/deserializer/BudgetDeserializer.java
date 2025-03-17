package com.message_service.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.Budget;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class BudgetDeserializer implements Deserializer<Budget> {
    ObjectMapper objectMapper= new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Budget deserialize(String s, byte[] bytes) {
        Budget budget= null;
        try{
            budget= objectMapper.readValue(bytes, Budget.class);
        }catch (Exception e){
            throw new RuntimeException("X Failed to Deserialize Message", e);
        }
        return budget;
    }


    @Override
    public void close() {
    }
}
