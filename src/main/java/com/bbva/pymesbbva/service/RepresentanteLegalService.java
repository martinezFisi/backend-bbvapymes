package com.bbva.pymesbbva.service;

import com.bbva.pymesbbva.dto.RepresentanteLegalDTO;

public interface RepresentanteLegalService {

    RepresentanteLegalDTO findByDNI(String dni);

}
