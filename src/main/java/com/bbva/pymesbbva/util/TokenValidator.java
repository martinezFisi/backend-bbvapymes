package com.bbva.pymesbbva.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TokenValidator {

    private final List<String> tokens = new ArrayList<>();

    public String createToken(){
        var token = UUID.randomUUID().toString().substring(0, 4);
        tokens.add(token);
        return token;
    }


    public boolean validateToken(String token){
        var result = tokens.contains(token);
        tokens.remove(token);
        return result;
    }

    /*@Scheduled(cron = "${0 * * ? * * *}")
    public void clearTokens(){
        tokens.clear();
    }*/

}
