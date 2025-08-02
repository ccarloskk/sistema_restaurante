package com.pointparaense.SistemaResataurante.controller;

import com.pointparaense.SistemaResataurante.model.Produtos;
import com.pointparaense.SistemaResataurante.service.ProdutosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @GetMapping
    public List<Produtos> mostrar_prod(){
        return produtosService.listar_produto();
    }

    @PostMapping
    public Produtos criar_prod(@RequestBody Produtos produtos){
        return produtosService.criar_produto(produtos);
    }

    @PutMapping("/{id_prod}")
    public Produtos atulizar_prod(@PathVariable Long id_prod, @RequestBody Produtos produtos){
        return produtosService.atualizar_prod(id_prod, produtos);
    }

    @DeleteMapping("/{id_prod}")
    public void excluir_prod(@PathVariable Long id_prod){
        produtosService.excluir_prod(id_prod);
    }

}
