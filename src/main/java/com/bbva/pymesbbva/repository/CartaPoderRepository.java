package com.bbva.pymesbbva.repository;

import com.bbva.pymesbbva.entity.CartaPoderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaPoderRepository extends JpaRepository<CartaPoderEntity, String> {
}
