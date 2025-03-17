package com.budget_service.service;

import com.budget_service.entities.Budget;

public interface BudgetProducer {
    void sendBudgetData(Budget budget);
}
