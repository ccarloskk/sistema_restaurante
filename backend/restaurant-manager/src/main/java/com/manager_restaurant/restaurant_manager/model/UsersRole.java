package com.manager_restaurant.restaurant_manager.model;

public enum UsersRole {

    ADMIN("admin"),
    GARCON("garcon");

    private String role;

    UsersRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
