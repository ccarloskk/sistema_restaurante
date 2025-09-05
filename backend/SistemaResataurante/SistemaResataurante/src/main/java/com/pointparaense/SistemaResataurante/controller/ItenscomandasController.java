package com.pointparaense.SistemaResataurante.controller;

import com.pointparaense.SistemaResataurante.model.ItensComandas;
import com.pointparaense.SistemaResataurante.model.ItensComandasDTO;
import com.pointparaense.SistemaResataurante.service.ItenscomandasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens_comandas")
public class ItenscomandasController {

    private final ItenscomandasService itenscomandasService;

    public ItenscomandasController(ItenscomandasService itenscomandasService) {
        this.itenscomandasService = itenscomandasService;
    }

    @GetMapping
    public List<ItensComandas> listar_itensComandas(){
        return itenscomandasService.listar_itensComandas();
    }

    @GetMapping("/detalhes")
    public List<ItensComandasDTO> listarItensComDetalhes() {
        return itenscomandasService.listarItensComDetalhes();
    }

    @PostMapping()
    public ItensComandas criar_itensComandas(@RequestBody ItensComandas itensComandas){
        return itenscomandasService.criar_itensComandas(itensComandas);
    }

    @PutMapping("/{id_itens_comanda}")
    public ItensComandas atualizar_itenscomanda(@RequestBody Long id_itens_comanda, @RequestBody ItensComandas itensComandas){
        return itenscomandasService.atualizar_itenscomanda(id_itens_comanda, itensComandas);
    }

    @DeleteMapping("/{id_itens_comanda}")
    public void excluir_itensComandas(@PathVariable ItensComandas id_itens_comanda){
        itenscomandasService.excluir_itensComandas(id_itens_comanda);
    }
}
