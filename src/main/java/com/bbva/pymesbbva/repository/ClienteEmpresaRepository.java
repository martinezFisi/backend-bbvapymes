package com.bbva.pymesbbva.repository;

import com.bbva.pymesbbva.entity.ClienteEmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteEmpresaRepository extends JpaRepository<ClienteEmpresaEntity, String> {
}
