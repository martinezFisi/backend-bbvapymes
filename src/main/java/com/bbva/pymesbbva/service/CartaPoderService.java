package com.bbva.pymesbbva.service;

import com.bbva.pymesbbva.dto.CartaPoderDTO;

public interface CartaPoderService {

    CartaPoderDTO findByNumber(String number);

}
