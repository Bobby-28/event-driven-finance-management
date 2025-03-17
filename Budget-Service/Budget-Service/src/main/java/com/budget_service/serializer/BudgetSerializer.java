package com.budget_service.serializer;

import com.budget_service.entities.Budget;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class BudgetSerializer implements Serializer<Budget> {
    ObjectMapper objectMapper= new ObjectMapper();
    //@Autowired
    //ObjectMapper objectMapper;
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String s, Budget budget) {
        byte[] retVal= null;
        try{
            retVal= objectMapper.writeValueAsString(budget).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {
    }
}
