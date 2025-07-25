package com.pointparaense.SistemaResataurante.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id_usuario;
    private String nome_usuario;
    private String email;
    private String senha;
    private String tipo;

    public Usuarios(long id_usuario, String nome_usuario, String email, String senha, String tipo) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public long id_usuario() {
        return id_usuario;
    }

    public Usuarios setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
        return this;
    }

    public String nome_usuario() {
        return nome_usuario;
    }

    public Usuarios setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
        return this;
    }

    public String email() {
        return email;
    }

    public Usuarios setEmail(String email) {
        this.email = email;
        return this;
    }

    public String senha() {
        return senha;
    }

    public Usuarios setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String tipo() {
        return tipo;
    }

    public Usuarios setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }
}
