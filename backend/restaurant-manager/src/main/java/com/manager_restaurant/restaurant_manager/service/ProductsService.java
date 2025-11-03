package com.manager_restaurant.restaurant_manager.service;


import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> listProducts() {
        return productsRepository.findAll();
    }

    public Products searchProduct(Long id_product) {
        return productsRepository.findById(id_product)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Products createProduct(Products product) {
        return productsRepository.save(product);
    }

    public void deleteProduct(Long id_product) {
        productsRepository.deleteById(id_product);
    }

    public Products updateProduct(Long id_product, Products updatedProduct) {
        Products product = searchProduct(id_product);
        product.setName_product(updatedProduct.getName_product());
        product.setPrice_product(updatedProduct.getPrice_product());
        product.setDescription_product(updatedProduct.getDescription_product());
        return productsRepository.save(product);
    }
}
