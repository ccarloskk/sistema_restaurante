package com.manager_restaurant.restaurant_manager.dto;

public class ItemsOrdersDTO {
     private Long id_order_item;
     private Long id_product;
     private int quantity;
     private String notes;

    public ItemsOrdersDTO(Long id_order_item , Long id_product, int quantity, String notes) {
        this.id_order_item = id_order_item;
        this.id_product = id_product;
        this.quantity = quantity;
        this.notes = notes;
    }

    public Long getId_order_item() {
        return id_order_item;
    }

    public Long getId_product() {
        return id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
    }

}
