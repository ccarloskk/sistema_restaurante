package com.pointparaense.SistemaResataurante.service;

import com.pointparaense.SistemaResataurante.model.Comandas;
import com.pointparaense.SistemaResataurante.model.ItensComandas;
import com.pointparaense.SistemaResataurante.model.Produtos;
import com.pointparaense.SistemaResataurante.repository.ComandasRepository;
import com.pointparaense.SistemaResataurante.repository.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ComandasService {

    private ComandasRepository comandasRepository;
    private ProdutosRepository produtosRepository;

    public ComandasService(ComandasRepository comandasRepository, ProdutosRepository produtosRepository) {
        this.comandasRepository = comandasRepository;
        this.produtosRepository = produtosRepository;
    }

    public BigDecimal calculartotal(Comandas comandas) {
        BigDecimal total = BigDecimal.ZERO;
        if (comandas.getItensComandas() != null) {
            for (ItensComandas item : comandas.getItensComandas()) {
                Long idProduto = item.getProdutos().getId_prod();
                Produtos produto = produtosRepository.findById(idProduto)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + idProduto));

                BigDecimal preco = produto.getPreco_prod();
                total = total.add(preco.multiply(BigDecimal.valueOf(item.getQuantidade())));
            }
        }
        comandas.setTotal(total);
        comandasRepository.save(comandas);
        return total;
    }

    public BigDecimal excluirtotal(Comandas comandas){
        comandas.setTotal(BigDecimal.ZERO);
        comandasRepository.save(comandas);
        return BigDecimal.ZERO;
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
        comandas.setTotal(calculartotal(comandas));
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
        comandas.setTotal(calculartotal(comandas));
        return comandasRepository.save(comandas);
    }
}
