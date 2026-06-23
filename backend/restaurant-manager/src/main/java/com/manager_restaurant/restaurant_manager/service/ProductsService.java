package com.manager_restaurant.restaurant_manager.service;

import com.manager_restaurant.restaurant_manager.dto.ProductsDTO;
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
        Products product = searchProductId(idProduct);
        product.setStatus(false);
    }

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products searchProductId(Long idProduct) {
        return productsRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado ou inativo"));
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<Products> getActiveProducts() {
        return productsRepository.findByStatusTrue();
    }

    public ProductsDTO createProduct(ProductsDTO productsDTO) {
        Products product = new Products();

        product.setName_product(productsDTO.getName_product());
        product.setDescription_product(productsDTO.getDescription_product());
        product.setCategory_products(productsDTO.getCategory_products());
        product.setPrice_product(productsDTO.getPrice_product());
        product.setStatus(
                productsDTO.getStatusProduct() != null ? productsDTO.getStatusProduct() : true
        );

        Products savedProduct = productsRepository.save(product);

        return new ProductsDTO(
                savedProduct.getIdProduct(),
                savedProduct.getName_product(),
                savedProduct.getDescription_product(),
                savedProduct.getStatus(),
                savedProduct.getCategory_products(),
                savedProduct.getPrice_product()
        );
    }

    public void deleteProduct(Long idProduct) {
        productsRepository.deleteById(idProduct);
    }

    public Products updateProduct(Long idProduct, Products updatedProduct) {
        Products product = searchProductId(idProduct);
        product.setName_product(updatedProduct.getName_product());
        product.setDescription_product(updatedProduct.getDescription_product());
        product.setCategory_products(updatedProduct.getCategory_products());
        product.setPrice_product(updatedProduct.getPrice_product());
        product.setStatus(updatedProduct.getStatus());
        return productsRepository.save(product);
    }
}
