package com.bbva.pymesbbva.repository;

import com.bbva.pymesbbva.entity.RepresentanteLegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteLegalRepository extends JpaRepository<RepresentanteLegalEntity, String> {
}
