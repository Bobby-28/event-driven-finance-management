package com.token_service.impl;

import com.token_service.entities.RefreshToken;
import com.token_service.entities.User;
import com.token_service.repository.RefreshTokenRepository;
import com.token_service.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Override
    public RefreshToken create(User user) {
        RefreshToken refreshToken= RefreshToken.builder()
                .userId(user.getUserId())
                .tokenId(UUID.randomUUID().toString())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(7, ChronoUnit.DAYS))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Boolean verifyRefreshToken(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh Token" + refreshToken.getTokenId() + "is expired please login again to generate one");
        }
        else {
            return true;
        }
    }

    @Override
    public void deleteToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
