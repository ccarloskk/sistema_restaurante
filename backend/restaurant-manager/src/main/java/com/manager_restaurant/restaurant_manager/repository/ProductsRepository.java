package com.manager_restaurant.restaurant_manager.repository;

import com.manager_restaurant.restaurant_manager.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository <Products, Long  > {
    List<Products> findAllByStatusTrue();
    @Query("select p from Products p where p.idProduct = :id and p.status = true")
    Optional<Products> findActiveById(@Param("id") Long idProduct);
    List<Products> findByStatusTrue();
}
