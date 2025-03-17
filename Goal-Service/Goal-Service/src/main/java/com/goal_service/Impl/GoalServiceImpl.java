package com.goal_service.Impl;

import com.goal_service.enitities.Goal;
import com.goal_service.repository.GoalRepository;
import com.goal_service.service.GoalProducer;
import com.goal_service.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    GoalProducer goalProducer;
    @Override
    public Goal create(Goal goal) {
        if(goal==null){
            throw new IllegalArgumentException("The Goal is Empty!!!!");
        }
        goal.setGoalId(UUID.randomUUID().toString());
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> getAll(String userId) {
        return goalRepository.findByUserId(userId);
    }

    @Override
    public Goal getGoal(String userId, String goalName) {
        return goalRepository.findByUserIdAndGoalName(userId, goalName);
    }

    @Override
    public Goal update(Goal goal) {
        if(goal==null){
            throw new IllegalArgumentException("The Goal is Empty!!!!");
        }
        if(goal.getGoalAmount()<=goal.getCurrentAmount()){
            goal.setComplete(true);
            goalProducer.sendGoalData(goal);
        }
        return goalRepository.save(goal);
    }

    @Override
    public void deleteGoal(Goal goal) {
        if(goal==null){
            throw new IllegalArgumentException("The Goal is Empty!!!!!");
        }
        goalRepository.delete(goal);
    }
}
