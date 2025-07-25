package com.pointparaense.SistemaResataurante.controller;


import com.pointparaense.SistemaResataurante.model.Usuarios;
import com.pointparaense.SistemaResataurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return usuarioService.mostrar_user();
    }

    @PostMapping
    public Usuarios criar_user(@RequestBody Usuarios usuarios){
        return  usuarioService.salvar_user(usuarios);
    }
}
