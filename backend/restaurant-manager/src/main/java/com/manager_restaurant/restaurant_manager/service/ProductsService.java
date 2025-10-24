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
        product.setProduct_name(updatedProduct.getProduct_name());
        product.setProduct_price(updatedProduct.getProduct_price());
        product.setProduct_description(updatedProduct.getProduct_description());
        return productsRepository.save(product);
    }
}
