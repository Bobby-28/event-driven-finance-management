package com.budget_service.service;

import com.budget_service.entities.Budget;

import java.util.List;

public interface BudgetService {
    Budget create(Budget budget);
    List<Budget> getAll(String userId);
    Budget findBudget(String userId, String category);
    Budget update(Budget budget);

    void deleteBudget(Budget budget);
}