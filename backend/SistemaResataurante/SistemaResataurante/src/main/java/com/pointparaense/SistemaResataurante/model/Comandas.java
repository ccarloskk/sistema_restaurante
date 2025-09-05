package com.pointparaense.SistemaResataurante.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comandas")
public class Comandas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comanda;

    private String nome_cliente;
    private Date data;
    private String status;

    @Column(name = "total")
    private BigDecimal total;

    @OneToMany(mappedBy = "comandas")
    private List<ItensComandas> itensComandas;


    public Comandas() {}

    public Comandas(Long id_comanda, String nome_cliente, Date data, String status, BigDecimal total, List<ItensComandas> itensComandas) {
        this.id_comanda = id_comanda;
        this.nome_cliente = nome_cliente;
        this.data = data;
        this.status = status;
        this.total = total;
        this.itensComandas = itensComandas;
    }

    public Long getId_comanda() {
        return id_comanda;
    }

    public Comandas setId_comanda(Long id_comanda) {
        this.id_comanda = id_comanda;
        return this;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public Comandas setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Comandas setData(Date data) {
        this.data = data;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Comandas setStatus(String status) {
        this.status = status;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Comandas setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public List<ItensComandas> getItensComandas() {
        return itensComandas;
    }

    public Comandas setItensComandas(List<ItensComandas> itensComandas) {
        this.itensComandas = itensComandas;
        return this;
    }
}
