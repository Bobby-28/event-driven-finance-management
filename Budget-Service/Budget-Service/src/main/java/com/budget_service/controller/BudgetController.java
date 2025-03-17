package com.budget_service.controller;

import com.budget_service.entities.Budget;
import com.budget_service.request.BudgetRequest;
import com.budget_service.service.BudgetService;
import com.budget_service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/budget")
public class BudgetController {
    @Autowired
    BudgetService budgetService;

    @Autowired
    JwtService jwtService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Budget budget, @RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            budget.setUserId(jwtService.extractUserId(token));
            Budget saveBudget= budgetService.create(budget);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveBudget);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is Invalid!!!!!");
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            List<Budget> budget= budgetService.getAll(jwtService.extractUserId(token));
            return ResponseEntity.status(HttpStatus.OK).body(budget);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is Invalid!!!!");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BudgetRequest budgetRequest, @RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            Budget budget= budgetService.findBudget(jwtService.extractUserId(token), budgetRequest.getCategory());
            if(budgetRequest.getAmount()!=0){
                budget.setAmount(budgetRequest.getAmount());
            }
            if(budgetRequest.getStart()!=null){
                budget.setStart(budgetRequest.getStart());
            }
            if(budgetRequest.getEnd()!=null){
                budget.setEnd(budgetRequest.getEnd());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.update(budget));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The Token is Invalid!!!!");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(String category, @RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            Budget budget= budgetService.findBudget(jwtService.extractUserId(token), category);
            budgetService.deleteBudget(budget);
            return ResponseEntity.status(HttpStatus.OK).body("Budget Deleted!!!!");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The Token is Invalid!!!!");
        }
    }
}
