package com.budget_service.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetRequest {
    private String category;
    private Double amount;
    private String start;
    private String end;
}
