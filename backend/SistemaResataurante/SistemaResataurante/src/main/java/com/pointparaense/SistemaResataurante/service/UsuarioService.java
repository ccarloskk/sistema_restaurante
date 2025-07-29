package com.pointparaense.SistemaResataurante.service;

import com.pointparaense.SistemaResataurante.model.Usuarios;
import com.pointparaense.SistemaResataurante.repository.UsuarioRepository;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuarios> mostrar_user() {
        List<Usuarios> lista = usuarioRepository.findAll();
        System.out.println("Total usuários encontrados: " + lista.size());
        return lista;
    }

    public Usuarios salvar_user(Usuarios usuarios){
        return usuarioRepository.save(usuarios);
    }

    public void excluir_user(Long id_usuario){
        usuarioRepository.deleteById(id_usuario);
    }
}
