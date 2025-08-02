package com.pointparaense.SistemaResataurante.repository;

import com.pointparaense.SistemaResataurante.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository <Produtos, Long > {

}
