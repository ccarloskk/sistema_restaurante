package com.pointparaense.SistemaResataurante.service;

import com.pointparaense.SistemaResataurante.model.Comandas;
import com.pointparaense.SistemaResataurante.model.ItensComandas;
import com.pointparaense.SistemaResataurante.repository.ItenscomandasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItenscomandasService {

    private final ItenscomandasRepository itenscomandasRepository;

    public ItenscomandasService(ItenscomandasRepository ItenscomandasRepository) {
        this.itenscomandasRepository = ItenscomandasRepository;
    }

    public List<ItensComandas> listar_itensComandas(){
        List<ItensComandas> listar_itensComandas = itenscomandasRepository.findAll();
        return listar_itensComandas;
    }

    public ItensComandas buscar_itensComandasdas(Long id_itens_comanda){
        return itenscomandasRepository.findById(id_itens_comanda)
                .orElseThrow(()-> new RuntimeException("produto nao encontrado"));
    }

    public ItensComandas criar_itensComandas(ItensComandas itensComandas){
        return itenscomandasRepository.save(itensComandas);
    }

    public void excluir_itensComandas(Long id_itens_comanda){
        itenscomandasRepository.deleteById(id_itens_comanda);
    }

    public ItensComandas atualizar_itenscomanda(Long id_itens_comanda, ItensComandas itensComandas){
        ItensComandas itensComandas1 = buscar_itensComandasdas(id_itens_comanda);
        itensComandas1.setId_itens_comanda(itensComandas.getId_itens_comanda());
        itensComandas1.setProdutos(itensComandas.getProdutos());
        itensComandas1.setObservacoes(itensComandas.getObservacoes());
        itensComandas1.setProdutos(itensComandas.getProdutos());
        return itenscomandasRepository.save(itensComandas);
    }
}
