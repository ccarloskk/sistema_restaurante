package com.pointparaense.SistemaResataurante.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Itens_Comandas")
public class ItensComandas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_itens_comanda;

    @ManyToOne
    @JoinColumn(name = "comanda_id")
    private Comandas comandas;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produtos;

    private int quantidade;
    private String observacoes;

    public ItensComandas() {}

    public ItensComandas(Long id_itens_comanda, int quantidade, String observacoes) {
        this.id_itens_comanda = id_itens_comanda;
        this.quantidade = quantidade;
        this.observacoes = observacoes;
    }

    public Long getId_itens_comanda() {
        return id_itens_comanda;
    }

    public ItensComandas setId_itens_comanda(Long id_itens_comanda) {
        this.id_itens_comanda = id_itens_comanda;
        return this;
    }

    public Comandas getComandas() {
        return comandas;
    }

    public ItensComandas setComandas(Comandas comandas) {
        this.comandas = comandas;
        return this;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public ItensComandas setProdutos(Produtos produtos) {
        this.produtos = produtos;
        return this;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public ItensComandas setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public ItensComandas setObservacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }
}
