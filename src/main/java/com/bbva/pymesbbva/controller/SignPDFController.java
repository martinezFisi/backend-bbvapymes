package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.util.DigitalSigner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/sign")
public class SignPDFController {

    private final DigitalSigner digitalSigner;

    public SignPDFController(DigitalSigner digitalSigner) {
        this.digitalSigner = digitalSigner;
    }

    @PostMapping(value = "{pdfName}")
    public ResponseEntity<Void> sign(@PathVariable String pdfName) {
        var pdfGeneratedDTO = digitalSigner.sign(pdfName);

        return ResponseEntity.created(URI.create(pdfGeneratedDTO.getPdfURL()))
                             .header("pdf-name", pdfGeneratedDTO.getPdfName())
                             .header("pdf-url", pdfGeneratedDTO.getPdfURL())
                             .header("pdf-message", pdfGeneratedDTO.getMessage())
                             .build();
    }

}
