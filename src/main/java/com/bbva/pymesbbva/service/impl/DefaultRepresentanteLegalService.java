package com.bbva.pymesbbva.service.impl;

import com.bbva.pymesbbva.dto.RepresentanteLegalDTO;
import com.bbva.pymesbbva.mapper.RepresentanteLegalMapper;
import com.bbva.pymesbbva.repository.RepresentanteLegalRepository;
import com.bbva.pymesbbva.service.RepresentanteLegalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class DefaultRepresentanteLegalService implements RepresentanteLegalService {

    private final RepresentanteLegalRepository representanteLegalRepository;
    private final RepresentanteLegalMapper representanteLegalMapper;

    public DefaultRepresentanteLegalService(RepresentanteLegalRepository representanteLegalRepository, RepresentanteLegalMapper representanteLegalMapper) {
        this.representanteLegalRepository = representanteLegalRepository;
        this.representanteLegalMapper = representanteLegalMapper;
    }

    @Override
    public RepresentanteLegalDTO findByDNI(String dni) {
        var representanteLegalEntity = representanteLegalRepository.findById(dni)
                                                           .orElseThrow(() -> new EntityNotFoundException("Representante Legal with dni [" + dni + "] not found"));
        log.info("RepresentanteLegalEntity: {}", representanteLegalEntity);
        return representanteLegalMapper.entityToDTO(representanteLegalEntity);
    }
}
