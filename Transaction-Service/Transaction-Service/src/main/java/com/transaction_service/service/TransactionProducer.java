package com.transaction_service.service;

import com.transaction_service.entities.Transaction;

public interface TransactionProducer {
    void sendData(Transaction transaction);
}
