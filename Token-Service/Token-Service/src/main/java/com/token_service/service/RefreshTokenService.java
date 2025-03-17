package com.token_service.service;

import com.token_service.entities.RefreshToken;
import com.token_service.entities.User;

public interface RefreshTokenService {
    RefreshToken create(User user);
    Boolean verifyRefreshToken(RefreshToken refreshToken);

    void deleteToken(RefreshToken refreshToken);
    RefreshToken findByToken(String token);
}
