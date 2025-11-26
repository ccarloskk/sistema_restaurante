package com.manager_restaurant.restaurant_manager.dto;

import java.math.BigDecimal;

public class MenuDTO {
    private Long id_product;
    private String name_product;
    private String description_product;
    private BigDecimal price_product;

    public MenuDTO(Long id_product, String name_product, String description_product, BigDecimal price_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.description_product = description_product;
        this.price_product = price_product;
    }

    public Long id_product() {
        return id_product;
    }

    public MenuDTO setId_product(Long id_product) {
        this.id_product = id_product;
        return this;
    }

    public String getDescription_product() {
        return description_product;
    }

    public MenuDTO setDescription_product(String description_product) {
        this.description_product = description_product;
        return this;
    }

    public String getName_product() {
        return name_product;
    }

    public MenuDTO setName_product(String name_product) {
        this.name_product = name_product;
        return this;
    }

    public BigDecimal getPrice_product() {
        return price_product;
    }

    public MenuDTO setPrice_product(BigDecimal price_product) {
        this.price_product = price_product;
        return this;
    }




}
