package com.bbva.pymesbbva.controller;

import com.bbva.pymesbbva.dto.CartaPoderDTO;
import com.bbva.pymesbbva.service.CartaPoderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/cartas-poder", produces = APPLICATION_JSON_VALUE)
public class CartaPoderController {

    private final CartaPoderService cartaPoderService;

    public CartaPoderController(CartaPoderService cartaPoderService) {
        this.cartaPoderService = cartaPoderService;
    }

    @GetMapping(value = "{number}")
    public ResponseEntity<CartaPoderDTO> getById(@PathVariable String number){

        var cartaPoderDTO = cartaPoderService.findByNumber(number);

        return ResponseEntity.ok(cartaPoderDTO);
    }

}
