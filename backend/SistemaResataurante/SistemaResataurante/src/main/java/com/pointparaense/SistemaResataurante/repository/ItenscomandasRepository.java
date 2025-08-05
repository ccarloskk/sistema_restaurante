package com.pointparaense.SistemaResataurante.repository;

import com.pointparaense.SistemaResataurante.model.ItensComandas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItenscomandasRepository extends JpaRepository <ItensComandas, Long> {
}
