package com.manager_restaurant.restaurant_manager.controller;
import com.manager_restaurant.restaurant_manager.dto.ProductsDTO;
import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/menu/admin")
    public List<Products> getAdminMenu() {
        return productsService.getAllProducts();
    }

    @GetMapping("/menu")
    public List<ProductsDTO> getMenu() {
        return productsService.getActiveProducts();
    }

    @GetMapping("/{idProduct}")
    public Products getProductById(@PathVariable Long idProduct) {
        return productsService.searchProductId(idProduct);
    }

    @PostMapping("/create")
    public ProductsDTO createProduct(@RequestBody ProductsDTO product){
        return productsService.createProduct(product);
    }

    @PutMapping("/update/{idProduct}")
    public Products updateProduct(@PathVariable Long idProduct, @RequestBody Products product){
        return productsService.updateProduct(idProduct, product);
    }

    @PatchMapping("/{idProduct}/destatus")
    public ResponseEntity<Void> destatusProduct(@PathVariable Long idProduct) {
        productsService.destatusProduct(idProduct);
        return ResponseEntity.noContent().build();
    }
}
