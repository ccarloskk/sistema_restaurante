package com.manager_restaurant.restaurant_manager.controller;


import com.manager_restaurant.restaurant_manager.dto.MenuDTO;
import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.repository.ProductsRepository;
import com.manager_restaurant.restaurant_manager.service.ProductsService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/menu")
    public List<Products> getMenu() {
        return productsRepository.findAll().stream()
                .map(p -> new Products(p.getId_product(), p.getName_product(), p.getDescription_product(), p.getPrice_product()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Products createProduct(@RequestBody Products product){
        return productsService.createProduct(product);
    }

    @PutMapping("/{id_product}")
    public Products updateProduct(@PathVariable Long id_product, @RequestBody Products product){
        return productsService.updateProduct(id_product, product);
    }

    @DeleteMapping("/{id_product}")
    public void deleteProduct(@PathVariable Long id_product){
        productsService.deleteProduct(id_product);
    }

}
