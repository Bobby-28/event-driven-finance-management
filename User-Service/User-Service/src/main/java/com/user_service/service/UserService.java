package com.user_service.service;

import com.user_service.entities.Token;
import com.user_service.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User get(String userId);

    Token getToken(User user);
    User getByEmail(String email);
}
