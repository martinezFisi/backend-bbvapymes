package com.bbva.pymesbbva.service.impl;

import com.bbva.pymesbbva.dto.CartaPoderDTO;
import com.bbva.pymesbbva.mapper.CartaPoderMapper;
import com.bbva.pymesbbva.repository.CartaPoderRepository;
import com.bbva.pymesbbva.service.CartaPoderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class DefaultCartaPoderService implements CartaPoderService {

    private final CartaPoderRepository cartaPoderRepository;
    private final CartaPoderMapper cartaPoderMapper;

    public DefaultCartaPoderService(CartaPoderRepository cartaPoderRepository, CartaPoderMapper cartaPoderMapper) {
        this.cartaPoderRepository = cartaPoderRepository;
        this.cartaPoderMapper = cartaPoderMapper;
    }

    @Override
    public CartaPoderDTO findByNumber(String number) {
        var cartaPoderEntity = cartaPoderRepository.findById(number)
                                                                   .orElseThrow(() -> new EntityNotFoundException("Carta Poder with number [" + number + "] not found"));
        log.info("RepresentanteLegalEntity: {}", cartaPoderEntity);
        return cartaPoderMapper.entityToDTO(cartaPoderEntity);
    }

}
