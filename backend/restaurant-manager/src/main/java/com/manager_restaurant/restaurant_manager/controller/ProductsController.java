package com.manager_restaurant.restaurant_manager.controller;


import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<Products> listProducts(){
        return productsService.listProducts();
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
