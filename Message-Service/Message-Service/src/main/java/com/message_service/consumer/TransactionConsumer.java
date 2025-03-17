package com.message_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.Transaction;
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
public class TransactionConsumer{
    Logger logger= LoggerFactory.getLogger(TransactionConsumer.class);
    @Autowired
    JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper= new ObjectMapper();
    String from= "adityabedi3000@gmail.com";
    @Autowired
    UserRepository userRepository;
    @KafkaListener(topics = "budget-update-topic", groupId = "${spring.kafka.consumer-groups.transaction-event-group.group-id}")
    public void listen(String message){
        MimeMessage simpleMailMessage= javaMailSender.createMimeMessage();
        try{
        Transaction transaction= objectMapper.readValue(message, Transaction.class);
        User user= userRepository.findById(transaction.getUserId()).orElseThrow(()-> new RuntimeException("User for the given Id is Not Found !!!!!"));
        String to= user.getEmail();
        String subject= "Finance-Management-Application  [Transaction-Created]";
        String emessage= "<p>Transaction created!!!!!</p>" +
                "<table>"
                + "<tr>"
                +"<td>"
                +"Transaction-id"
                +"</td>"
                +"<td>"
                +transaction.getTransactionId()
                +"</td>"
                +"</tr>"
                + "<tr>"
                +"<td>"
                +"Amount"
                +"</td>"
                +"<td>"
                +transaction.getAmount()
                +"</td>"
                +"</tr>"
                + "<tr>"
                +"<td>"
                +"Category"
                +"</td>"
                +"<td>"
                +transaction.getCategory()
                +"</td>"
                +"</tr>"
                + "<tr>"
                +"<td>"
                +"Transaction-Date"
                +"</td>"
                +"<td>"
                +transaction.getTransactionDate()
                +"</td>"
                +"</tr>"
                + "<tr>"
                +"<td>"
                +"Type"
                +"</td>"
                +"<td>"
                +transaction.getType()
                +"</td>"
                +"</tr>"
                +"</table>";
            MimeMessageHelper helper= new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emessage, true);
            helper.setFrom(from);
            javaMailSender.send(simpleMailMessage);
            logger.info("Email has been sent..........");
        }catch (Exception e){
            e.printStackTrace();
        }
        // for testing purpose
        try{
            Transaction transaction= objectMapper.readValue(message, Transaction.class);
            System.out.println("New Transaction Received!!!!!!!");
            System.out.println("Transaction Id : " + transaction.getTransactionId());
            System.out.println("Transaction Amount : " + transaction.getAmount());
            System.out.println("Transaction Type : " + transaction.getType());
            System.out.println("Transaction Category : " + transaction.getCategory());
            System.out.println("Transaction Date : " + transaction.getTransactionDate());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
