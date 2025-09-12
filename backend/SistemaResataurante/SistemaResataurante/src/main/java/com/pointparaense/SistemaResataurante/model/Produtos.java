package com.pointparaense.SistemaResataurante.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name= "produtos")
public class Produtos {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_prod;

    private String nome_prod;
    private String descricao_prod;

    @Column(name = "preco_prod", nullable = false)
    private BigDecimal preco_prod;

    @OneToMany(mappedBy = "produtos")
    private List<ItensComandas> itensComandas;

    public Produtos(){}

    public Produtos(Long id_prod, String nome_prod, String descricao_prod, BigDecimal preco_prod) {
        this.id_prod = id_prod;
        this.nome_prod = nome_prod;
        this.descricao_prod = descricao_prod;
        this.preco_prod = preco_prod;
    }

    public Long getId_prod() {
        return id_prod;
    }

    public Produtos setId_prod(Long id_prod) {
        this.id_prod = id_prod;
        return this;
    }

    public String getDescricao_prod() {
        return descricao_prod;
    }

    public Produtos setDescricao_prod(String descricao_prod) {
        this.descricao_prod = descricao_prod;
        return this;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public Produtos setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
        return this;
    }

    public BigDecimal getPreco_prod() {
        return preco_prod;
    }

    public Produtos setPreco_prod(BigDecimal preco_prod) {
        this.preco_prod = preco_prod;
        return this;
    }

    public List<ItensComandas> getItensComandas() {
        return itensComandas;
    }

    public Produtos setItensComandas(List<ItensComandas> itensComandas) {
        this.itensComandas = itensComandas;
        return this;
    }
}
