package com.manager_restaurant.restaurant_manager.service;

import com.manager_restaurant.restaurant_manager.dto.ProductsDTO;
import com.manager_restaurant.restaurant_manager.model.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductsMapper {

    public Products toEntity(ProductsDTO dto) {
        Products entity = new Products();
        entity.setIdProduct(dto.getIdProduct());
        entity.setName_product(dto.getNameProduct());
        entity.setDescription_product(dto.getDescriptionProduct());
        entity.setCategory_products(dto.getCategoryProducts());

        return entity;
    }

    public ProductsDTO toResponseDTO(Products entity) {
        ProductsDTO dto = new ProductsDTO();
        dto.setIdProduct(entity.getIdProduct());
        dto.setNameProduct(entity.getName_product());
        dto.setDescriptionProduct(entity.getDescription_product());
        dto.setCategoryProducts(entity.getCategory_products());

        if (entity.getPrice_product() != null) {
            dto.setPriceProduct(entity.getPrice_product().toPlainString());
        }
        return dto;
    }
}
