package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.dto.SolicitudDTO;
import com.bbva.pymesbbva.service.SolicitudService;
import com.bbva.pymesbbva.util.PDFGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/solicitudes", produces = APPLICATION_JSON_VALUE)
public class SolicitudController {

    private final SolicitudService solicitudService;
    private final PDFGenerator pdfGenerator;

    public SolicitudController(SolicitudService solicitudService, PDFGenerator pdfGenerator) {
        this.solicitudService = solicitudService;
        this.pdfGenerator = pdfGenerator;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSolicitud(HttpServletRequest request, @RequestBody SolicitudDTO solicitudDTO){
        var pdfGeneratedDTO = pdfGenerator.generatePDF(solicitudDTO);
        var solicitudId = solicitudService.save(solicitudDTO);

        var uri = URI.create(request.getRequestURI() + "/" + solicitudId);
        return ResponseEntity.created(uri)
                             .header("pdf-name", pdfGeneratedDTO.getPdfName())
                             .header("pdf-url", pdfGeneratedDTO.getPdfURL())
                             .header("pdf-message", pdfGeneratedDTO.getMessage())
                             .build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SolicitudDTO> getById(@PathVariable String id){

        var solicitudDTO = solicitudService.findById(id);

        return ResponseEntity.ok(solicitudDTO);
    }

}
