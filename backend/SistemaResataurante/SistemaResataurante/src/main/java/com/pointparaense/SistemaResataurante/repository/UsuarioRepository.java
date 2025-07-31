package com.pointparaense.SistemaResataurante.repository;

import com.pointparaense.SistemaResataurante.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuarios, Long>{
}
