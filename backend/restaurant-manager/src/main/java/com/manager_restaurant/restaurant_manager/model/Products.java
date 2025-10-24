package com.manager_restaurant.restaurant_manager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name= "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    private String product_name;
    private String product_description;

    @Column(name = "product_price", nullable = false)
    private BigDecimal product_price;

    @OneToMany(mappedBy = "product")
    private List<OrderItems> OrderItems;

    public Products(){}

    public Products(Long id_product, String product_name, String product_description, BigDecimal product_price) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
    }

    public Long getId_product() {
        return id_product;
    }

    public Products setId_product(Long id_product) {
        this.id_product = id_product;
        return this;
    }

    public String getProduct_description() {
        return product_description;
    }

    public Products setProduct_description(String product_description) {
        this.product_description = product_description;
        return this;
    }

    public String getProduct_name() {
        return product_name;
    }

    public Products setProduct_name(String product_name) {
        this.product_name = product_name;
        return this;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public Products setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
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
