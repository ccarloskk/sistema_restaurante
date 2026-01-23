package com.manager_restaurant.restaurant_manager.repository;


import com.manager_restaurant.restaurant_manager.dto.ItensComandasDTO;
import com.manager_restaurant.restaurant_manager.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    @Query("""
                SELECT new com.manager_restaurant.restaurant_manager.dto.ItensComandasDTO(
                    oi.id_order_item,
                    o.id_order,
                    o.name_customer,
                    p.name_product,
                    oi.quantity,
                    oi.notes
                )
                FROM OrderItems oi
                JOIN oi.order o
                JOIN oi.product p
            """)
    List<ItensComandasDTO> seacherWithDetails();
}