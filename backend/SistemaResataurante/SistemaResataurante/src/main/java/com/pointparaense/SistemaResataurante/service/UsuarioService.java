package com.pointparaense.SistemaResataurante.service;

import com.pointparaense.SistemaResataurante.model.Usuarios;
import com.pointparaense.SistemaResataurante.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuarios> listar_user() {
        List<Usuarios> lista = usuarioRepository.findAll();
        return lista;
    }

    public Usuarios buscar_user(Long id_usuario){
        return usuarioRepository.findById(id_usuario)
                .orElseThrow(()-> new RuntimeException("usuário nao encontrado"));
    }

    public Usuarios salvar_user(Usuarios usuarios){
        return usuarioRepository.save(usuarios);
    }

    public void excluir_user(Long id_usuario){
         usuarioRepository.deleteById(id_usuario);
    }

    public Usuarios atualizar_user(Long id_usuarios, Usuarios atualizar_user){
            Usuarios usuarios = buscar_user(id_usuarios);
            usuarios.setNome_usuario(atualizar_user.getNome_usuario());
            usuarios.setEmail(atualizar_user.getEmail());
            usuarios.setSenha(atualizar_user.getSenha());
            return usuarioRepository.save(usuarios);
    }
}
