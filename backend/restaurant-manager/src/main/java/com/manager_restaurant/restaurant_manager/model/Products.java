package com.manager_restaurant.restaurant_manager.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name= "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    private String name_product;
    private String description_product;

    @Column(name = "price_product", nullable = false)
    private BigDecimal price_product;

    @OneToMany(mappedBy = "product")
    private List<OrderItems> OrderItems;

    public Products(){}

    public Products(Long id_product, String name_product, String description_product, BigDecimal price_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.description_product = description_product;
        this.price_product = price_product;
    }

    public Long getId_product() {
        return id_product;
    }

    public Products setId_product(Long id_product) {
        this.id_product = id_product;
        return this;
    }

    public String getName_product() {
        return name_product;
    }

    public Products setName_product(String name_product) {
        this.name_product = name_product;
        return this;
    }

    public String getDescription_product() {
        return description_product;
    }

    public Products setDescription_product(String description_product) {
        this.description_product = description_product;
        return this;
    }

    public BigDecimal getPrice_product() {
        return price_product;
    }

    public Products setPrice_product(BigDecimal price_product) {
        this.price_product = price_product;
        return this;
    }

    public List<OrderItems> getOrder_items() {
        return OrderItems;
    }

    public Products setOrder_items(List<OrderItems> OrderItems) {
        this.OrderItems = OrderItems;
        return this;
    }
}
