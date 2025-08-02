package com.pointparaense.SistemaResataurante.controller;


import com.pointparaense.SistemaResataurante.model.Usuarios;
import com.pointparaense.SistemaResataurante.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuarios> mostrar_user() {
        return usuarioService.listar_user();
    }

    @PostMapping
    public Usuarios criar_user(@RequestBody Usuarios usuarios){
        return  usuarioService.criar_user(usuarios);
    }

    @PutMapping("/{id_usuario}")
    public Usuarios atualizar_user(@PathVariable Long id_usuario, @RequestBody Usuarios usuarios){
        return usuarioService.atualizar_user(id_usuario, usuarios);
    }

    @DeleteMapping("/{id_usuario}")
    public void excluir_user (@PathVariable Long id_usuario){
        usuarioService.excluir_user(id_usuario);
    }
}
