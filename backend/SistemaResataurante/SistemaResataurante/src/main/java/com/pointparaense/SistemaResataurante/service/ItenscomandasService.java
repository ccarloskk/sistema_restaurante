package com.pointparaense.SistemaResataurante.service;

import com.pointparaense.SistemaResataurante.model.Comandas;
import com.pointparaense.SistemaResataurante.model.ItensComandas;
import com.pointparaense.SistemaResataurante.model.ItensComandasDTO;
import com.pointparaense.SistemaResataurante.model.Produtos;
import com.pointparaense.SistemaResataurante.repository.ComandasRepository;
import com.pointparaense.SistemaResataurante.repository.ItenscomandasRepository;
import com.pointparaense.SistemaResataurante.repository.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItenscomandasService {

    private final ItenscomandasRepository itenscomandasRepository;
    private  final ComandasService comandasService;
    private  final ComandasRepository comandasRepository;
    private final ProdutosRepository produtosRepository;

    public ItenscomandasService(ItenscomandasRepository itenscomandasRepository, ComandasService comandasService, ComandasRepository comandasRepository, ProdutosRepository produtosRepository) {
        this.itenscomandasRepository = itenscomandasRepository;
        this.comandasService = comandasService;
        this.comandasRepository = comandasRepository;
        this.produtosRepository = produtosRepository;
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
        ItensComandas itemSalvo = itenscomandasRepository.save(itensComandas);

        Comandas comanda = comandasRepository.findById(itemSalvo.getComandas().getId_comanda())
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
        Produtos produto = produtosRepository.findById(itemSalvo.getProdutos().getId_prod())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        itemSalvo.setComandas(comanda);
        itemSalvo.setProdutos(produto);
        comandasService.atualizar_valor(comanda);
        return itemSalvo;
    }

    public ItensComandas  excluir_itensComandas(ItensComandas itensComandas){
        ItensComandas itensexcluido = itenscomandasRepository.save(itensComandas);

        Comandas comandas = itenscomandasRepository.findById(itensexcluido.getId_itens_comanda())
                .orElseThrow(() -> new RuntimeException("Comanda nao encontrada")).getComandas();

        Comandas comanda = itensexcluido.getComandas();
        comandasService.atualizar_valor(comanda);
        itenscomandasRepository.delete(itensexcluido);
        return itensexcluido;
    }

    public List<ItensComandasDTO> listarItensComDetalhes() {
        return itenscomandasRepository.buscarItensComDetalhes();
    }

    public ItensComandas atualizar_itenscomanda(Long id_itens_comanda, ItensComandas itensComandas){
        ItensComandas itensComandas1 = buscar_itensComandasdas(id_itens_comanda);
        itensComandas1.setProdutos(itensComandas.getProdutos());
        itensComandas1.setQuantidade(itensComandas.getQuantidade());
        itensComandas1.setObservacoes(itensComandas.getObservacoes());
        ItensComandas itemAtualizado = itenscomandasRepository.save(itensComandas1);
        comandasService.atualizar_valor(itemAtualizado.getComandas());
        return itemAtualizado;
    }
}
