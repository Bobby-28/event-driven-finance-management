package com.transaction_service.contoller;

import com.transaction_service.entities.Transaction;
import com.transaction_service.service.JwtService;
import com.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    @Autowired
    JwtService jwtService;

    @Autowired
    TransactionService transactionService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Transaction transaction, @RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            String userId= jwtService.extractUserId(token);
            transaction.setUserId(userId);
            Transaction saveTransaction= transactionService.create(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveTransaction);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Token is Invalid");
        }
    }
    @GetMapping()
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String authHeader){
        if(authHeader!=null && authHeader.startsWith("Bearer")){
            String token= authHeader.substring(7);
            String userId= jwtService.extractUserId(token);
            List<Transaction> transactions= transactionService.getFromUserId(userId);
            if(transactions==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Transaction with User Id Not found!!!!!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(transactions);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The Token is UNAUTHORIZED");
        }
    }
}
