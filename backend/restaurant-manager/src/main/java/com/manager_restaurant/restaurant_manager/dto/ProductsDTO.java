package com.manager_restaurant.restaurant_manager.dto;

public class ProductsDTO {

    private Long idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private String categoryProducts;
    private String priceProduct;
    private Boolean statusProduct;

    public ProductsDTO() {
    }

    public ProductsDTO(Long idProduct, String nameProduct, String descriptionProduct, String categoryProducts, String priceProduct, Boolean statusProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.categoryProducts = categoryProducts;
        this.priceProduct = priceProduct;
        this.statusProduct = statusProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public ProductsDTO setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public ProductsDTO setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
        return this;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public ProductsDTO setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
        return this;
    }

    public String getCategoryProducts() {
        return categoryProducts;
    }

    public ProductsDTO setCategoryProducts(String categoryProducts) {
        this.categoryProducts = categoryProducts;
        return this;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public ProductsDTO setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
        return this;
    }

    public Boolean getStatusProduct() {
        return statusProduct;
    }

    public ProductsDTO setStatusProduct(Boolean statusProduct) {
        this.statusProduct = statusProduct;
        return this;
    }
}
