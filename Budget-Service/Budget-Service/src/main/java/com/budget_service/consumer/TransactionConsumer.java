package com.budget_service.consumer;

import com.budget_service.entities.Budget;
import com.budget_service.entities.Transaction;
import com.budget_service.repositroy.BudgetRepository;
import com.budget_service.service.BudgetProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {
    Logger logger= LoggerFactory.getLogger(TransactionConsumer.class);
    @Autowired
    BudgetProducer budgetProducer;
    @Autowired
    BudgetRepository budgetRepository;
    @KafkaListener(topics = "budget-update-topic", groupId = "transaction-consumer-group")
    public void listen(Transaction transaction){
        try {
            Budget budget= budgetRepository.findByUserIdAndCategory(transaction.getUserId(), transaction.getCategory());
            if(budget!=null){
                Double totalAmount= budget.getSpentAmount()+transaction.getAmount();
                budget.setSpentAmount(totalAmount);
                if(budget.getSpentAmount()>budget.getAmount()){
                    budgetProducer.sendBudgetData(budget);
                }
                logger.info("The Budget Updated on {}", budget);
                budgetRepository.save(budget);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
