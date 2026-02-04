package com.manager_restaurant.restaurant_manager.dto;

import java.math.BigDecimal;

public class OrderItemsDTO {
    private String nameClient;
    private Long id_order;
    private Long id_product;
    private String name_product;
    private Long quantity;
    private BigDecimal total;

    public OrderItemsDTO() {}

    public OrderItemsDTO(String nameClient ,Long id_order,Long id_product , String name_product, Long quantity, BigDecimal total) {
        this.nameClient = nameClient;
        this.id_order = id_order;
        this.id_product = id_product;
        this.name_product = name_product;
        this.quantity = quantity;
        this.total = total;
    }

    public String nameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
