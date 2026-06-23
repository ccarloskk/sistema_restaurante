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

    public List<ProductsDTO> getActiveProducts() {
        return productsRepository.findByStatusTrue().stream()
                .map(product -> new ProductsDTO(
                        product.getIdProduct(),
                        product.getName_product(),
                        product.getDescription_product(),
                        product.getStatus(),
                        product.getCategory_products(),
                        product.getPrice_product()
                ))
                .toList();
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

    public ProductsDTO updateProduct(Long idProduct, ProductsDTO productsDTO) {
        Products product = searchProductId(idProduct);
        product.setName_product(productsDTO.getName_product());
        product.setDescription_product(productsDTO.getDescription_product());
        product.setCategory_products(productsDTO.getCategory_products());
        product.setPrice_product(productsDTO.getPrice_product());
        product.setStatus(productsDTO.getStatusProduct());

        Products updatedProduct = productsRepository.save(product);
        return new ProductsDTO(
                updatedProduct.getIdProduct(),
                updatedProduct.getName_product(),
                updatedProduct.getDescription_product(),
                updatedProduct.getStatus(),
                updatedProduct.getCategory_products(),
                updatedProduct.getPrice_product()
        );
    }
}
