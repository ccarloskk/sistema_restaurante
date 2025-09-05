package com.pointparaense.SistemaResataurante.model;

public class ItensComandasDTO {
    private Long id_itens_comanda;
    private String nome_cliente;
    private String nome_prod;
    private int quantidade;
    private String observacoes;

    public ItensComandasDTO(Long id_itens_comanda, String nome_cliente, String nome_prod, int quantidade, String observacoes) {
        this.id_itens_comanda = id_itens_comanda;
        this.nome_cliente = nome_cliente;
        this.nome_prod = nome_prod;
        this.quantidade = quantidade;
        this.observacoes = observacoes;
    }

    public Long getId_itens_comanda() {
        return id_itens_comanda;
    }

    public ItensComandasDTO setId_itens_comanda(Long id_itens_comanda) {
        this.id_itens_comanda = id_itens_comanda;
        return this;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public ItensComandasDTO setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
        return this;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public ItensComandasDTO setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
        return this;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public ItensComandasDTO setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public ItensComandasDTO setObservacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }
}