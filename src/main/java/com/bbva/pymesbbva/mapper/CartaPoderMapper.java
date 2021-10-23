package com.bbva.pymesbbva.mapper;

import com.bbva.pymesbbva.dto.CartaPoderDTO;
import com.bbva.pymesbbva.entity.CartaPoderEntity;
import org.springframework.stereotype.Component;

@Component
public class CartaPoderMapper {

    public CartaPoderDTO entityToDTO(CartaPoderEntity cartaPoderEntity){
        return CartaPoderDTO.builder()
                .numeroPartida(cartaPoderEntity.getNumeroPartida())
                .empresa(cartaPoderEntity.getEmpresa())
                .poderdante(cartaPoderEntity.getPoderdante())
                .rolPoderdante(cartaPoderEntity.getRolPoderdante())
                .tipo(cartaPoderEntity.getTipo())
                .estado(cartaPoderEntity.getEstado())
                .apoderado(cartaPoderEntity.getApoderado())
                .build();
    }

}
