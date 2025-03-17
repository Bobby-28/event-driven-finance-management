package com.budget_service.entities;

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
@Table(name = "budgets")
public class Budget {
    @Id
    private String budgetId;
    private Double amount;
    private String category;
    private Double spentAmount;
    private String start;
    private String end;
    private String userId;
}

