package com.goal_service.repository;

import com.goal_service.enitities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, String> {
    List<Goal> findByUserId(String userId);

    Goal findByUserIdAndGoalName(String userId, String goalName);
}
