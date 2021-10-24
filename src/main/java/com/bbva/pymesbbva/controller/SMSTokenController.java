package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.client.SMSInfobipClient;
import com.bbva.pymesbbva.util.DigitalSigner;
import com.bbva.pymesbbva.util.TokenValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/sms/token", produces = APPLICATION_JSON_VALUE)
public class SMSTokenController {

    private final SMSInfobipClient smsInfobipClient;
    private final TokenValidator tokenValidator;

    public SMSTokenController(SMSInfobipClient smsInfobipClient, TokenValidator tokenValidator) {
        this.smsInfobipClient = smsInfobipClient;
        this.tokenValidator = tokenValidator;
    }

    @PostMapping(value = "send/{number}")
    public ResponseEntity<String> sendToken(@PathVariable String number) {
        var token = tokenValidator.createToken();
        return smsInfobipClient.sendSms(number, token);
    }

    @PostMapping(value = "validate/{token}")
    public ResponseEntity<Void> validateToken(@PathVariable String token) {
        return tokenValidator.validateToken(token) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
