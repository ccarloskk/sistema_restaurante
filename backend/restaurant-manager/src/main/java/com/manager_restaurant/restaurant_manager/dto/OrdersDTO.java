package com.manager_restaurant.restaurant_manager.dto;

import com.manager_restaurant.restaurant_manager.model.Order;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersDTO {
    private Long id_order;
    private String name_customer;
    private Date date_order;
    private String status;
    private BigDecimal total;

    public  OrdersDTO(){}

    public OrdersDTO(Long id_order, String name_customer, Date date_order, String status, BigDecimal total) {
        this.id_order = id_order;
        this.name_customer = name_customer;
        this.date_order = date_order;
        this.status = status;
        this.total = total;
    }

    public Long getId_order() {
        return id_order;
    }

    public OrdersDTO setId_order(Long id_order) {
        this.id_order = id_order;
        return this;
    }

    public String getName_customer() {
        return name_customer;
    }

    public OrdersDTO setName_customer(String name_customer) {
        this.name_customer = name_customer;
        return this;
    }

    public Date getDate_order() {
        return date_order;
    }

    public OrdersDTO setDate_order(Date date_order) {
        this.date_order = date_order;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrdersDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrdersDTO setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }
}
