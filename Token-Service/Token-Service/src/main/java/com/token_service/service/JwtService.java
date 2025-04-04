package com.token_service.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String extractUserId(String token);
    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);
    Date extractExpiration(String token);
    Boolean isTokenExpired(String token);
    Boolean validateToken(String token);
    String generateToken(String userId);
    String createToken(Map<String, Object> claims, String userId);
    Claims extractAllClaims(String token);
    Key getSignKey();
}
