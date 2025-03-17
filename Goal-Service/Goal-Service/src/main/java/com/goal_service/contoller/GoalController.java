package com.goal_service.contoller;

import com.goal_service.enitities.Goal;
import com.goal_service.request.UpdateRequest;
import com.goal_service.service.GoalService;
import com.goal_service.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/goal")
public class GoalController {

    Logger logger= LoggerFactory.getLogger(GoalController.class);

    @Autowired
    GoalService goalService;

    @Autowired
    JwtService jwtService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestHeader("Authorization") String authHeader, @RequestBody Goal goal){
        logger.info("Received request in Goal Service with Authorization header: {}", authHeader);
        try{
            if(authHeader!=null && authHeader.startsWith("Bearer")){
                String token= authHeader.substring(7);
                goal.setUserId(jwtService.extractUserId(token));
                return ResponseEntity.status(HttpStatus.CREATED).body(goalService.create(goal));
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is Invalid!!!!");
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            List<Goal> goal= goalService.getAll(jwtService.extractUserId(token));
            return ResponseEntity.status(HttpStatus.OK).body(goal);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is Invalid!!!!");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String authHeader, @RequestBody UpdateRequest updateRequest){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            Goal goal= goalService.getGoal(jwtService.extractUserId(token), updateRequest.getGoalName());
            if(updateRequest.getGoalName()!= null){
                goal.setGoalName(updateRequest.getGoalName());
            }if(updateRequest.getGoalAmount()!=0){
                goal.setGoalAmount(updateRequest.getGoalAmount());
            }if(updateRequest.getCurrentAmount()!=0){
                goal.setCurrentAmount(updateRequest.getCurrentAmount());
            }if(updateRequest.getTargetDate()!=null){
                goal.setTargetDate(updateRequest.getTargetDate());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(goalService.update(goal));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Jwt Token!!!!!");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(String goalName, @RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            Goal goal= goalService.getGoal(jwtService.extractUserId(token), goalName);
            goalService.deleteGoal(goal);
            return ResponseEntity.status(HttpStatus.OK).body("The Goal Has been Deleted!!!!");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Jwt Token!!!!!!!");
        }
    }
}
