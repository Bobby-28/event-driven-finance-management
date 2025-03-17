package com.budget_service.repositroy;

import com.budget_service.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
    List<Budget> findByUserId(String userId);
    Budget findByUserIdAndCategory(String userId, String category);
}
