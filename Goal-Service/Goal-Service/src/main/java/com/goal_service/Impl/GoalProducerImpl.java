package com.goal_service.Impl;

import com.goal_service.enitities.Goal;
import com.goal_service.service.GoalProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class GoalProducerImpl implements GoalProducer {
    private final KafkaTemplate<String, Goal> kafkaTemplate;
    @Autowired
    public GoalProducerImpl(KafkaTemplate<String, Goal> kafkaTemplate){
        this.kafkaTemplate= kafkaTemplate;
    }
    @Override
    public void sendGoalData(Goal goal) {
        Message<Goal> message= MessageBuilder.withPayload(goal)
                .setHeader(KafkaHeaders.TOPIC, "goal-alert-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
