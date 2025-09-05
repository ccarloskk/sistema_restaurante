package com.pointparaense.SistemaResataurante.repository;

import com.pointparaense.SistemaResataurante.model.ItensComandas;
import com.pointparaense.SistemaResataurante.model.ItensComandasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItenscomandasRepository extends JpaRepository <ItensComandas, Long> {

    @Query("""
    SELECT new com.pointparaense.SistemaResataurante.model.ItensComandasDTO(
        ic.id_itens_comanda,
        c.nome_cliente,
        p.nome_prod,
        ic.quantidade,
        ic.observacoes
    )
    FROM ItensComandas ic
    JOIN ic.comandas c
    JOIN ic.produtos p
    """)

    List<ItensComandasDTO> buscarItensComDetalhes();
}
