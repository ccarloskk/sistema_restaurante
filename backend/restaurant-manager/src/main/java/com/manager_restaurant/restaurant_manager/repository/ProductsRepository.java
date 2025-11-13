package com.manager_restaurant.restaurant_manager.repository;

import com.manager_restaurant.restaurant_manager.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository <Products, Long > {
}
