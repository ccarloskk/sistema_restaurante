package com.manager_restaurant.restaurant_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name= "products")
@CrossOrigin(origins = "*")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    private String name_product;
    private String description_product;
    private Boolean status = true;

    @Column(name = "category_products", nullable = false)
    private String category_products;

    @Column(name = "price_product", nullable = false)
    private BigDecimal price_product;

    @OneToMany(mappedBy = "product")
    private List<OrderItems> OrderItems;

    public Products(){}

    public Products(Long idProduct, String name_product, String description_product, String category_products, BigDecimal price_product, Boolean status) {
        this.idProduct = idProduct;
        this.name_product = name_product;
        this.description_product = description_product;
        this.category_products = category_products;
        this.price_product = price_product;
        this.status = status;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public Products setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
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

    public String getCategory_products() {
        return category_products;
    }

    public Products setCategory_products(String category_products) {
        this.category_products = category_products;
        return this;
    }
    public Boolean getStatus() {
        return status;
    }
    public Products setStatus(Boolean status) {
        this.status = status;
        return this;
    }
}
