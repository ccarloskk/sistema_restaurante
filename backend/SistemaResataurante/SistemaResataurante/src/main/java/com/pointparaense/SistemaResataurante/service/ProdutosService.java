package com.pointparaense.SistemaResataurante.service;

import com.pointparaense.SistemaResataurante.model.Produtos;
import com.pointparaense.SistemaResataurante.model.Usuarios;
import com.pointparaense.SistemaResataurante.repository.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {

    private ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public List<Produtos> listar_produto(){
        List<Produtos> lista = produtosRepository.findAll();
        return lista;
    }

    public Produtos buscar_prod(Long id_prod){
        return produtosRepository.findById(id_prod)
                .orElseThrow(()-> new RuntimeException("produto nao encontrado"));
    }

    public Produtos criar_produto(Produtos produtos){
        return produtosRepository.save(produtos);
    }

    public void excluir_prod(Long id_usuario){
        produtosRepository.deleteById(id_usuario);
    }

    public Produtos atualizar_prod(Long id_prod, Produtos atualizar_prod){
        Produtos produtos = buscar_prod(id_prod);
        produtos.setNome_prod(atualizar_prod.getNome_prod());
        produtos.setPreco_prod(atualizar_prod.getPreco_prod());
        produtos.setDescricao_prod(atualizar_prod.getDescricao_prod());
        return produtosRepository.save(produtos);
    }
}
