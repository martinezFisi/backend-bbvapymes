package com.bbva.pymesbbva.service;

import com.bbva.pymesbbva.dto.ClienteEmpresaDTO;

public interface ClienteEmpresaService {

    ClienteEmpresaDTO findByRUC(String ruc);

}
