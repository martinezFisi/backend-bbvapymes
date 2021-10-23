package com.bbva.pymesbbva.service.impl;

import com.bbva.pymesbbva.dto.ClienteEmpresaDTO;
import com.bbva.pymesbbva.mapper.ClienteEmpresaMapper;
import com.bbva.pymesbbva.repository.ClienteEmpresaRepository;
import com.bbva.pymesbbva.service.ClienteEmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class DefaultClienteEmpresaService implements ClienteEmpresaService {

    private final ClienteEmpresaRepository clienteEmpresaRepository;
    private final ClienteEmpresaMapper clienteEmpresaMapper;

    public DefaultClienteEmpresaService(ClienteEmpresaRepository clienteEmpresaRepository, ClienteEmpresaMapper clienteEmpresaMapper) {
        this.clienteEmpresaRepository = clienteEmpresaRepository;
        this.clienteEmpresaMapper = clienteEmpresaMapper;
    }

    @Override
    public ClienteEmpresaDTO findByRUC(String ruc) {
        var clienteEmpresaEntity = clienteEmpresaRepository.findById(ruc)
                                                           .orElseThrow(() -> new EntityNotFoundException("ClienteEmpresa with ruc [" + ruc + "] not found"));
        log.info("ClienteEmpresaEntity: {}", clienteEmpresaEntity);
        return clienteEmpresaMapper.entityToDTO(clienteEmpresaEntity);
    }

}
