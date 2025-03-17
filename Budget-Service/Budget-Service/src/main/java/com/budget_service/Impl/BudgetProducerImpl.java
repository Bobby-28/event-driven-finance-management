package com.budget_service.Impl;

import com.budget_service.entities.Budget;
import com.budget_service.service.BudgetProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class BudgetProducerImpl implements BudgetProducer {
    private final KafkaTemplate<String, Budget> kafkaTemplate;
    @Autowired
    public BudgetProducerImpl(KafkaTemplate<String, Budget> kafkaTemplate){
        this.kafkaTemplate= kafkaTemplate;
    }
    @Override
    public void sendBudgetData(Budget budget) {
        Message<Budget> message= MessageBuilder.withPayload(budget)
                .setHeader(KafkaHeaders.TOPIC, "budget-alert-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
