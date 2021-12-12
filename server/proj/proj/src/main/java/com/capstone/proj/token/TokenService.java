package com.capstone.proj.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    // TokenDAO passed in constructor
    @Autowired
    public TokenService() {}

    public int blackListToken(Token token) {
        // logic to add token to token blacklist
        return 1;
    }
}
