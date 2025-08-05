package com.pointparaense.SistemaResataurante.repository;

import com.pointparaense.SistemaResataurante.model.Comandas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandasRepository extends JpaRepository <Comandas, Long> {
}
