package com.transaction_service.Impl;

import com.transaction_service.entities.Transaction;
import com.transaction_service.service.TransactionProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducerImpl implements TransactionProducer {
    Logger logger= LoggerFactory.getLogger(TransactionProducerImpl.class);

    KafkaTemplate<String, Transaction> kafkaTemplate;

    @Autowired
    TransactionProducerImpl(KafkaTemplate<String ,Transaction> kafkaTemplate){
        this.kafkaTemplate= kafkaTemplate;
    }
    @Override
    public void sendData(Transaction transaction) {
        Message<Transaction> message= MessageBuilder.withPayload(transaction)
                .setHeader(KafkaHeaders.TOPIC, "budget-update-topic").build();
        logger.info("The transaction has been sent to kafka");
        kafkaTemplate.send(message);
    }
}
