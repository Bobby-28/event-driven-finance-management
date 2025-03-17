package com.user_service.impl;

import com.user_service.entities.Token;
import com.user_service.entities.User;
import com.user_service.repository.UserRepository;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public User get(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException(userId + "is not found!!!!!!"));
    }
    @Override
    public Token getToken(User user) {
        Token token= restTemplate.postForObject("http://localhost:7071/api/v1/users/login", user, Token.class);
        return token;
    }
    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
