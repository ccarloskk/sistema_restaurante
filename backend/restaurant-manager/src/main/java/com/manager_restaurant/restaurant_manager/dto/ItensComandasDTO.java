package com.manager_restaurant.restaurant_manager.dto;

public class ItensComandasDTO {
    private Long id_items_order;
    private Long id_order;
    private String name_client;
    private String name_prod;
    private int quantity;
    private String notes;

    public ItensComandasDTO(Long id_items_order,Long id_order ,String name_client, String name_prod, int quantity, String notes) {
        this.id_items_order = id_items_order;
        this.id_order = id_order;
        this.name_client = name_client;
        this.name_prod = name_prod;
        this.quantity = quantity;
        this.notes = notes;
    }

    public Long getId_items_order() {
        return id_items_order;
    }

    public String getName_client() {
        return name_client;
    }

    public Long getId_order() {
        return id_order;
    }

    public ItensComandasDTO setId_order(Long id_order) {
        this.id_order = id_order;
        return this;
    }

    public ItensComandasDTO setName_client(String name_client) {
        this.name_client = name_client;
        return this;
    }

    public String getName_prod() {
        return name_prod;
    }

    public ItensComandasDTO setName_prod(String name_prod) {
        this.name_prod = name_prod;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItensComandasDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public ItensComandasDTO setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}