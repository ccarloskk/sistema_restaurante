package com.manager_restaurant.restaurant_manager.dto;


import com.manager_restaurant.restaurant_manager.model.UsersRole;

public record RegisterDTO(
        String email,
        String password,
        UsersRole role,
        String user_name) {
}
