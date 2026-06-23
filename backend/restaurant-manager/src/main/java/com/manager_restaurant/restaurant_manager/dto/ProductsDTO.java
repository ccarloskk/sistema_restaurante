package com.manager_restaurant.restaurant_manager.dto;

import java.math.BigDecimal;

public class ProductsDTO {

    private Long idProduct;
    private String name_product;
    private String description_product;
    private String category_products;
    private BigDecimal price_product;
    private Boolean statusProduct;

    public ProductsDTO(Long idProduct, String nameProduct, String descriptionProduct, Boolean status, String categoryProducts, BigDecimal priceProduct) {
        this.idProduct = idProduct;
        this.name_product = nameProduct;
        this.description_product = descriptionProduct;
        this.statusProduct = status;
        this.category_products = categoryProducts;
        this.price_product = priceProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public String getName_product() {
        return name_product;
    }

    public String getDescription_product() {
        return description_product;
    }

    public String getCategory_products() {
        return category_products;
    }

    public BigDecimal getPrice_product() {
        return price_product;
    }

    public Boolean getStatusProduct() {
        return statusProduct;
    }
}