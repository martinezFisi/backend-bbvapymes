package com.bbva.pymesbbva.service.impl;

import com.bbva.pymesbbva.dto.SolicitudDTO;
import com.bbva.pymesbbva.mapper.SolicitudMapper;
import com.bbva.pymesbbva.repository.SolicitudRepository;
import com.bbva.pymesbbva.service.SolicitudService;
import com.bbva.pymesbbva.util.PDFGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class DefaultSolicitudService implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final SolicitudMapper solicitudMapper;

    public DefaultSolicitudService(SolicitudRepository solicitudRepository, SolicitudMapper solicitudMapper) {
        this.solicitudRepository = solicitudRepository;
        this.solicitudMapper = solicitudMapper;
    }

    @Override
    public String save(SolicitudDTO solicitudDTO) {
        var solicitudEntity = solicitudMapper.dtoToEntity(solicitudDTO);

        return solicitudRepository.save(solicitudEntity).getRucDni();
    }

    @Override
    public SolicitudDTO findById(String id) {
        var solicitudEntity = solicitudRepository.findById(id)
                                                 .orElseThrow(() -> new EntityNotFoundException("Solicitud with id [" + id + "] not found"));;

        return solicitudMapper.entityToDTO(solicitudEntity);
    }
}
