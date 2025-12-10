package com.manager_restaurant.restaurant_manager.controller;


import com.manager_restaurant.restaurant_manager.dto.ProductsDTO;
import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.repository.ProductsRepository;
import com.manager_restaurant.restaurant_manager.service.ProductsMapper;
import com.manager_restaurant.restaurant_manager.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductsController {

    private final ProductsMapper productsMapper;

    @Autowired
    private final ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    public ProductsController(ProductsService productsService, ProductsMapper productsMapper) {
        this.productsService = productsService;
        this.productsMapper = productsMapper;
    }

    @GetMapping("/admin/menu")
    public List<Products> getAdminMenu() {
        return productsRepository.findAll().stream()
                .map(p -> new Products(p.getIdProduct(), p.getName_product(), p.getDescription_product(), p.getCategory_products(), p.getPrice_product(), p.getStatus()))
                .collect(Collectors.toList());
    }

    @GetMapping("/menu")
    public List<Products> getMenu() {
        return productsRepository.findByStatusTrue().stream()
                .map(p -> new Products(p.getIdProduct(), p.getName_product(), p.getDescription_product(), p.getCategory_products(), p.getPrice_product(), p.getStatus()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Products> getProductById(@PathVariable Long idProduct) {
        return productsRepository.findById(idProduct)
                .map(produto -> ResponseEntity.ok(
                        new Products (produto.getIdProduct(), produto.getName_product(), produto.getDescription_product(), produto.getCategory_products(), produto.getPrice_product(), produto.getStatus()
                        )
                ))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Products createProduct(@RequestBody Products product){
        return productsService.createProduct(product);
    }

    @PutMapping("/{idProduct}")
    public ProductsDTO updateProduct(@PathVariable Long idProduct, @RequestBody ProductsDTO  product){
        Products toUpdate = productsMapper.toEntity(product);
        Products updated = productsService.updateProduct(idProduct, toUpdate);
        return productsMapper.toResponseDTO(updated);
    }

    @PatchMapping("/{idProduct}/destatus")
    public ResponseEntity<Void> destatusProduct(@PathVariable Long idProduct) {
        productsService.destatusProduct(idProduct);
        return ResponseEntity.noContent().build();
    }
}
