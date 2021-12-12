package com.capstone.proj.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {

    // TokenDAO passed in constructor
    @Autowired
    public TokenService() {}

    public Token generateToken(Long id) {
        Token token = new Token(
                id,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                "Secret Key");
        return token;
    }

    public int authenticateToken(int id, Token token) {
        // logic to authenticate token
        return 1;
    }

    public int blackListToken(Token token) {
        // logic to add token to token blacklist
        return 1;
    }
}
