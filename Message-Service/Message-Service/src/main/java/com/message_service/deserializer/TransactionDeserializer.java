package com.message_service.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.Transaction;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TransactionDeserializer implements Deserializer<Transaction> {
    ObjectMapper objectMapper= new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Transaction deserialize(String s, byte[] bytes) {
        Transaction transaction= null;
        try{
            transaction= objectMapper.readValue(bytes, Transaction.class);
        }catch (Exception e){
            throw new RuntimeException("X Failed to Deserialize the Transaction Message", e);
        }
        return transaction;
    }

    @Override
    public void close() {
    }
}
