package com.transaction_service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction_service.entities.Transaction;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionSerializer implements Serializer<Transaction> {

    ObjectMapper objectMapper= new ObjectMapper();
    //    or user this
//    @AutoWired
//    ObjectMapper objectMapper

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, Transaction transaction) {
        byte[] retVal= null;
        try {
            retVal= objectMapper.writeValueAsString(transaction).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {
    }
}
