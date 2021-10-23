package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.dto.RepresentanteLegalDTO;
import com.bbva.pymesbbva.service.RepresentanteLegalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/representantes-legales", produces = APPLICATION_JSON_VALUE)
public class RepresentanteLegalController {

    private final RepresentanteLegalService representanteLegalService;

    public RepresentanteLegalController(RepresentanteLegalService representanteLegalService) {
        this.representanteLegalService = representanteLegalService;
    }

    @GetMapping(value = "{dni}")
    public ResponseEntity<RepresentanteLegalDTO> getById(@PathVariable String dni){

        var representanteLegalDTO = representanteLegalService.findByDNI(dni);

        return ResponseEntity.ok(representanteLegalDTO);
    }

}
