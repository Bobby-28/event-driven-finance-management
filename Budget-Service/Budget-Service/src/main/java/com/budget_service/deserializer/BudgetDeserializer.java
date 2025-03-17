package com.budget_service.deserializer;

import com.budget_service.entities.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class BudgetDeserializer implements Deserializer<Transaction> {

    ObjectMapper objectMapper= new ObjectMapper();
//    @Autowired
//    ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Transaction deserialize(String s, byte[] bytes) {
        Transaction transaction= null;
        try{
            transaction= objectMapper.readValue(bytes, Transaction.class);
        }catch (Exception e){
            throw new RuntimeException("❌ Failed to deserialize message", e);
        }
        return transaction;
    }

    @Override
    public void close() {
    }
}
