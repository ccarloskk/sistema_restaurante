package com.pointparaense.SistemaResataurante.service;

import com.pointparaense.SistemaResataurante.model.Comandas;
import com.pointparaense.SistemaResataurante.model.Produtos;
import com.pointparaense.SistemaResataurante.repository.ComandasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandasService {

    private ComandasRepository comandasRepository;

    public ComandasService(ComandasRepository comandasRepository) {
        this.comandasRepository = comandasRepository;
    }

    public List<Comandas> Listar_comandas(){
        List<Comandas> listar_comandas = comandasRepository.findAll();
        return listar_comandas;
    }

    public Comandas buscar_comandas(Long id_comandas){
            return comandasRepository.findById(id_comandas)
                    .orElseThrow(()-> new RuntimeException("produto nao encontrado"));
    }

    public Comandas criar_comanda(Comandas comandas){
        return comandasRepository.save(comandas);
    }

    public void excluir_comanda(Long id_comanda){
        comandasRepository.deleteById(id_comanda);
    }

    public Comandas atualizar_comanda(Long id_comandas, Comandas atualizar_comanda){
        Comandas comandas = buscar_comandas(id_comandas);
        comandas.setNome_cliente(atualizar_comanda.getNome_cliente());
        comandas.setData(atualizar_comanda.getData());
        comandas.setStatus(atualizar_comanda.getStatus());
        comandas.setTotal(atualizar_comanda.getTotal());
        return comandasRepository.save(comandas);
    }

}
