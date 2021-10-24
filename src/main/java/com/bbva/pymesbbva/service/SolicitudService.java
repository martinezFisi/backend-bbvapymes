package com.bbva.pymesbbva.service;

import com.bbva.pymesbbva.dto.SolicitudDTO;

public interface SolicitudService {

    String save(SolicitudDTO solicitudDTO);

    SolicitudDTO findById(String id);

}
