package com.message_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message_service.entities.Goal;
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
public class GoalConsumer{

    Logger logger= LoggerFactory.getLogger(BudgetConsumer.class);
    private final ObjectMapper objectMapper= new ObjectMapper();
    private final String from= "adityabedi3000@gmail.com";
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @KafkaListener(topics = "goal-alert-topic", groupId = "${spring.kafka.consumer-groups.goal-alert-group.group-id}")
    public void listen(String message){
        MimeMessage simpleMailMessage= javaMailSender.createMimeMessage();
        try{
        Goal goal= objectMapper.readValue(message, Goal.class);
        User user= userRepository.findById(goal.getUserId()).orElseThrow(()-> new RuntimeException("The user Id is not Found!!!!!"));
        String to= user.getEmail();
        String subject= "Finance-Management-Application [Goal-Alert]";
        String emessage= "<h1>"
                + "Your Goal has been achieved for the "
                + goal.getGoalName()
                + "goal name"
                + "</h1>"
                + "<p>"
                + "Congratulate you achieved your goal amount :)"
                + "</p>"
                + "<p>"
                + "The Current Amount is : "
                + goal.getCurrentAmount()
                + "</p>";
            MimeMessageHelper helper= new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emessage, true);
            helper.setFrom(from);
            javaMailSender.send(simpleMailMessage);
            logger.info("Email has been sent.......");
        }catch (Exception e){
            e.printStackTrace();
        }
        // for testing purpose
        try{
            Goal goal= objectMapper.readValue(message, Goal.class);
            System.out.println("Goal Achieved For the "+ goal.getGoalName());
            System.out.println("Current Amount is : " + goal.getCurrentAmount());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
