package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.dto.ClienteEmpresaDTO;
import com.bbva.pymesbbva.service.ClienteEmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/clientes", produces = APPLICATION_JSON_VALUE)
public class ClienteEmpresaController {

    private final ClienteEmpresaService clienteEmpresaService;

    public ClienteEmpresaController(ClienteEmpresaService clienteEmpresaService) {
        this.clienteEmpresaService = clienteEmpresaService;
    }

    @GetMapping(value = "{ruc}")
    public ResponseEntity<ClienteEmpresaDTO> getById(@PathVariable String ruc){

        var citizenDto = clienteEmpresaService.findByRUC(ruc);

        return ResponseEntity.ok(citizenDto);
    }

}
