package com.transaction_service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "transactions")
public class Transaction {
    @Id
    String transactionId;
    Double amount;
    String category;
    String type;
    String transactionDate;
    String userId;
}
