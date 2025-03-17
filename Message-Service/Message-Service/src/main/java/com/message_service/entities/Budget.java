package com.message_service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    private String budgetId;
    private Double amount;
    private String category;
    private Double spentAmount;
    private String start;
    private String end;
    private String userId;
}
