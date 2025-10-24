package com.manager_restaurant.restaurant_manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order_item;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Products product;

    private int quantity;
    private String notes;

    public OrderItems() {}

    public OrderItems(Long id_order_item, int quantity, String notes) {
        this.id_order_item = id_order_item;
        this.quantity = quantity;
        this.notes = notes;
    }

    public Long getId_order_item() {
        return id_order_item;
    }

    public OrderItems setId_order_item(Long id_order_item) {
        this.id_order_item = id_order_item;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderItems setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Products getProduct() {
        return product;
    }

    public OrderItems setProduct(Products product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderItems setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public OrderItems setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
