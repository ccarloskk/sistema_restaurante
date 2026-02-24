package com.manager_restaurant.restaurant_manager.dto;

import java.util.List;

public class UpdateOrderDTO {
    private List<ItemsOrdersDTO> items;

    public UpdateOrderDTO(List<ItemsOrdersDTO> items) {
        this.items = items;
    }

    public List<ItemsOrdersDTO> getItems() {
        return items;
    }

    public UpdateOrderDTO setItems(List<ItemsOrdersDTO> items) {
        this.items = items;
        return this;
    }
}
