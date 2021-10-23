package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.client.SMSInfobipClient;
import com.bbva.pymesbbva.util.DigitalSigner;
import com.bbva.pymesbbva.util.EmailSender;
import com.bbva.pymesbbva.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final SMSInfobipClient smsInfobipClient;
    private final EmailSender emailSender;
    private final PDFGenerator pdfGenerator;
    private final DigitalSigner digitalSigner;

    @Autowired
    public HelloController(SMSInfobipClient smsInfobipClient, EmailSender emailSender, PDFGenerator pdfGenerator, DigitalSigner digitalSigner) {
        this.smsInfobipClient = smsInfobipClient;
        this.emailSender = emailSender;
        this.pdfGenerator = pdfGenerator;
        this.digitalSigner = digitalSigner;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Hackaton - App initialized!!";
    }

    @GetMapping("/sms")
    public String sms(@RequestParam(name = "receptor") String receptor, @RequestParam(name = "message") String message) {
        var responseEntity = smsInfobipClient.sendSms(receptor, "BBVA PERU", message);
        return responseEntity.getStatusCodeValue() + " - " + responseEntity.getBody();
    }

    @GetMapping("/email")
    public String email(@RequestParam(name = "receptor") String receptor, @RequestParam(name = "message") String message) {
        return emailSender.sendEmail(receptor, message);
    }

    @GetMapping("/pdf")
    public String pdf(@RequestParam(name = "text") String text) {
        return pdfGenerator.generatePDF(text);
    }

    @GetMapping("/sign")
    public String sign(@RequestParam(name = "pdfName") String pdfName) {
        return digitalSigner.sign(pdfName);
    }
}
