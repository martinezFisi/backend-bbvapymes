package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.util.EmailSender;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/email")
public class EmailController {

    private final EmailSender emailSender;

    public EmailController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping(value = "{email}")
    public HttpEntity<Void> sendEmail(HttpServletRequest request, @PathVariable String email) {
        var pdfUrl = request.getHeader("pdf-url");
        return emailSender.sendEmail(email, pdfUrl) ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

}
