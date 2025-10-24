package com.manager_restaurant.restaurant_manager.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;

    private String customer_name;
    private Date date_order;
    private String status;

    @Column(name = "total")
    private BigDecimal total;

    @OneToMany(mappedBy = "order")
    private List<OrderItems> OrderItems;


    public Order() {}

    public Order(Long id_order, String customer_name, Date date_order, String status, BigDecimal total, List<OrderItems> OrderItems) {
        this.id_order = id_order;
        this.customer_name = customer_name;
        this.date_order = date_order;
        this.status = status;
        this.total = total;
        this.OrderItems = OrderItems;
    }

    public Long getId_order() {
        return id_order;
    }

    public Order setId_order(Long id_order) {
        this.id_order = id_order;
        return this;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public Order setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
        return this;
    }

    public Date getDate_order() {
        return date_order;
    }

    public Order setDate_order(Date date_order) {
        this.date_order = date_order;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Order setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public List<OrderItems> getOrder_items() {
        return OrderItems;
    }

    public Order setOrder_items(List<OrderItems> OrderItems) {
        this.OrderItems = OrderItems;
        return this;
    }
}
