package com.message_service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    String transactionId;
    Double amount;
    String category;
    String type;
    String transactionDate;
    String userId;
}
