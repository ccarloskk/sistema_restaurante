package com.pointparaense.SistemaResataurante.controller;

import com.pointparaense.SistemaResataurante.model.Comandas;
import com.pointparaense.SistemaResataurante.service.ComandasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comandas")
public class ComandasController {

    private final ComandasService comandasService;

    public ComandasController(ComandasService comandasService) {
        this.comandasService = comandasService;
    }

    @GetMapping
    public List<Comandas> listar_comandas(){
        return comandasService.Listar_comandas();
    }

    @PostMapping
    public Comandas criar_comandas(@RequestBody Comandas comandas){
        return comandasService.criar_comanda(comandas);
    }

    @PutMapping("/{id_comanda}")
    public Comandas atualizar_comandas(@PathVariable("id_comanda") Long id_comandas, @RequestBody Comandas comandas){
        return comandasService.atualizar_comanda(id_comandas, comandas);
    }

    @DeleteMapping("/{id_comanda}")
    public void excluir_comandas(@PathVariable("id_comanda") Long id_comandas){
        comandasService.excluir_comanda(id_comandas);
    }

}
