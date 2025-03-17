package com.user_consumer.user_consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConsumerConfig {
    /*This configuration can be used when you don't want to make a new objectmapper constructor when you make objectmapper instance
    then use this bean to when you want to use objectmapper in this case you don't have to make a new constructor in every call
    you just need to make the object and annotate it with @AutoWired then spring will automatically call this bean*/
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
