package com.manager_restaurant.restaurant_manager.dto;

public class TotalPublicDTO {

    private Long id_product;
    private Integer quantity;

    public TotalPublicDTO(Integer quantity, Long id_product) {
        this.quantity = quantity;
        this.id_product = id_product;
    }

    public Long getId_product() {
        return id_product;
    }

    public TotalPublicDTO setId_product(Long id_product) {
        this.id_product = id_product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public TotalPublicDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
