package com.budget_service.Impl;

import com.budget_service.entities.Budget;
import com.budget_service.repositroy.BudgetRepository;
import com.budget_service.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    BudgetRepository budgetRepository;
    @Override
    public Budget create(Budget budget) {
        if(budget==null){
            throw new IllegalArgumentException("The Budget is Empty");
        }
        budget.setBudgetId(UUID.randomUUID().toString());
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAll(String userId) {
        return budgetRepository.findByUserId(userId);
    }

    @Override
    public Budget findBudget(String userId, String category) {
        return budgetRepository.findByUserIdAndCategory(userId, category);
    }

    @Override
    public Budget update(Budget budget) {
        if(budget==null){
            throw new IllegalArgumentException("The Budget is Empty");
        }
        return budgetRepository.save(budget);
    }

    @Override
    public void deleteBudget(Budget budget) {
        if(budget==null){
            throw new IllegalArgumentException("The Budget is Empty");
        }
        budgetRepository.delete(budget);
    }
}
