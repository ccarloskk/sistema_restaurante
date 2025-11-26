package com.manager_restaurant.restaurant_manager.service;


import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    @Transactional
    public void destatusProduct(Long idProduct) {
        Products product = searchProduct(idProduct);
        product.setStatus(false);
    }

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products searchProduct(Long idProduct) {
        return productsRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado ou inativo"));
    }

    public Products createProduct(Products product) {
        return productsRepository.save(product);
    }

    public void deleteProduct(Long idProduct) {
        productsRepository.deleteById(idProduct);
    }

    public Products updateProduct(Long idProduct, Products updatedProduct) {
        Products product = searchProduct(idProduct);
        product.setName_product(updatedProduct.getName_product());
        product.setDescription_product(updatedProduct.getDescription_product());
        product.setCategory_products(updatedProduct.getCategory_products());
        product.setPrice_product(updatedProduct.getPrice_product());
        product.setStatus(updatedProduct.getStatus());
        return productsRepository.save(product);
    }
}
