package com.goal_service.service;

import com.goal_service.enitities.Goal;

import java.util.List;

public interface GoalService {
    Goal create(Goal goal);
    List<Goal> getAll(String userId);

    Goal getGoal(String userId, String goalName);

    Goal  update(Goal goal);

    void deleteGoal(Goal goal);
}
