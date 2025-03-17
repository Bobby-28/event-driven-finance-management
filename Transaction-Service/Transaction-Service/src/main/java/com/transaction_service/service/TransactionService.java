package com.transaction_service.service;

import com.transaction_service.entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction create(Transaction transaction);
//    List<Transaction> getAll();

    List<Transaction> getFromUserId(String userId);
}
