package com.transaction_service.Impl;

import com.transaction_service.entities.Transaction;
import com.transaction_service.repository.TransactionRepository;
import com.transaction_service.service.TransactionProducer;
import com.transaction_service.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionProducer transactionProducer;
    @Override
    public Transaction create(Transaction transaction) {
        if(transaction==null){
            throw new IllegalArgumentException("The Transaction is Empty");
        }
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionDate(LocalDateTime.now().toString());
        if(transaction.getType().equalsIgnoreCase("EXPENSE")){
            transactionProducer.sendData(transaction);
        }
        return transactionRepository.save(transaction);
    }
    @Override
    public List<Transaction> getFromUserId(String userId) {
        return transactionRepository.findByUserId(userId);
    }
}
