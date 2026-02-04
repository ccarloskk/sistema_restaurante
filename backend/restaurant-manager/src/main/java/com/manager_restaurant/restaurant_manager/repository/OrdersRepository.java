package com.manager_restaurant.restaurant_manager.repository;

import com.manager_restaurant.restaurant_manager.dto.OrdersDTO;
import com.manager_restaurant.restaurant_manager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository <Order, Long> {
    @Query("""
        SELECT new com.manager_restaurant.restaurant_manager.dto.OrdersDTO(
            o.id_order,
            o.name_customer,
            o.date_order,
            o.status,
            o.total
        )
        FROM Order o
    """)
    List<OrdersDTO> listOrdersDTO();

    @Query("""
        SELECT new com.manager_restaurant.restaurant_manager.dto.OrdersDTO(
            o.id_order,
            o.name_customer,
            o.date_order,
            o.status,
            o.total
        )
        FROM Order o
        WHERE o.id_order = :id_order
    """)
    List<OrdersDTO> findAllOrders(@Param("id_order") Long id_order);
}
