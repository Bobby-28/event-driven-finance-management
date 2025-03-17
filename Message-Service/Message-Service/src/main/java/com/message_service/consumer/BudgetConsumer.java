package com.message_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.Budget;
import com.message_service.entities.User;
import com.message_service.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class BudgetConsumer {
    Logger logger= LoggerFactory.getLogger(BudgetConsumer.class);
    private final ObjectMapper objectMapper= new ObjectMapper();
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;
    String from= "adityabedi3000@gmail.com";
    @KafkaListener(topics = "budget-alert-topic", groupId = "${spring.kafka.consumer-groups.budget-alert-group.group-id}")
    public void listen(String message){
        MimeMessage simpleMailMessage= javaMailSender.createMimeMessage();
        try{
        Budget budget= objectMapper.readValue(message, Budget.class);
        User user= userRepository.findById(budget.getUserId()).orElseThrow(()-> new RuntimeException("User Id is Not Found!!!!!"));
        String to= user.getEmail();
        String subject= "Finance-Management-Application  [Budget-Alert]";
        String emessage= "<h1>Budget Exceed For The"+ budget.getCategory() + "Category" + "</h1>"
                + "<p>"
                + "The Spend Amount :"
                +budget.getSpentAmount()
                +"exceed the budget Amount :"
                +budget.getAmount()
                +"by total margin of "
                +(budget.getSpentAmount()-budget.getAmount())
                + "</p>"
                + "<p>"
                + "Please Kindly Update the budget amount or else you can delete it!!!!!!"
                + "</p>";
            MimeMessageHelper helper= new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(from);
            helper.setText(emessage, true);
            javaMailSender.send(simpleMailMessage);
            logger.info("Email has been Sent.....");
        }catch (Exception e){
            e.printStackTrace();
        }
        // for testing purpose
        try{
            Budget budget= objectMapper.readValue(message, Budget.class);
            System.out.println("Budget Exceed of " + budget.getCategory() + "Category");
            System.out.println("Spent Amount = " + budget.getSpentAmount() + "which is higher than Set Amount = " + budget.getAmount());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}