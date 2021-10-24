package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.service.SolicitudService;
import com.bbva.pymesbbva.util.DigitalSigner;
import com.bbva.pymesbbva.util.EmailSender;
import com.bbva.pymesbbva.util.PDFGenerator;
import com.bbva.pymesbbva.util.PropertiesUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/send/pdf/signed")
public class HelloController {

    private final SolicitudService solicitudService;
    private final PropertiesUtil propertiesUtil;
    private final PDFGenerator pdfGenerator;
    private final DigitalSigner digitalSigner;
    private final EmailSender emailSender;

    public HelloController(SolicitudService solicitudService, PropertiesUtil propertiesUtil, PDFGenerator pdfGenerator, DigitalSigner digitalSigner, EmailSender emailSender) {
        this.solicitudService = solicitudService;
        this.propertiesUtil = propertiesUtil;
        this.pdfGenerator = pdfGenerator;
        this.digitalSigner = digitalSigner;
        this.emailSender = emailSender;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Hackaton - App initialized!!";
    }

    @PostMapping(value = "{email}")
    public ResponseEntity<Void> enviarPDF(@PathVariable String email){
        var solicitudDTO = solicitudService.findById("20728444182-72777438");

        var pdfGeneratedDTO = pdfGenerator.generatePDF(solicitudDTO);
        var pdfGeneratedDTO2 = digitalSigner.sign(pdfGeneratedDTO.getPdfName());

        emailSender.sendEmail(email, pdfGeneratedDTO2.getPdfURL());

        return ResponseEntity.ok().build();
    }

}
