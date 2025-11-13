package com.manager_restaurant.restaurant_manager.dto;
import java.math.BigDecimal;

public class ItemsSumDTO {

    private Long id_product;
    private BigDecimal price_product;
    private Long quantity;
    private BigDecimal totalPublic;

    public ItemsSumDTO(){}

    public ItemsSumDTO(Long id_product, BigDecimal price_product, BigDecimal totalPublic, Long quantity) {
        this.id_product = id_product;
        this.price_product = price_product;
        this.quantity = quantity;
        this.totalPublic = totalPublic;
    }

    public Long getId_product() {
        return id_product;
    }

    public ItemsSumDTO setId_product(Long id_product) {
        this.id_product = id_product;
        return this;
    }

    public BigDecimal getPrice_product() {
        return price_product;
    }

    public ItemsSumDTO setPrice_product(BigDecimal price_product) {
        this.price_product = price_product;
        return this;
    }

    public BigDecimal getTotalPublic() {
        return totalPublic;
    }

    public ItemsSumDTO setTotalPublic(BigDecimal totalPublic) {
        this.totalPublic = totalPublic;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ItemsSumDTO setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
