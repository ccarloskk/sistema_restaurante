package com.manager_restaurant.restaurant_manager.repository;


import com.manager_restaurant.restaurant_manager.dto.ItensComandasDTO;
import com.manager_restaurant.restaurant_manager.dto.OrderItemsDTO;
import com.manager_restaurant.restaurant_manager.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("""
        SELECT new com.manager_restaurant.restaurant_manager.dto.OrderItemsDTO(
            oi.order.name_customer,
            oi.order.id_order,
            oi.product.idProduct,
            oi.product.name_product,
            SUM(oi.quantity),
            oi.order.total
        )
        FROM OrderItems oi
        WHERE oi.order.id_order = :id_order
        GROUP BY
            oi.order.name_customer,
            oi.order.id_order,
            oi.product.idProduct,
            oi.product.name_product,
            oi.order.total
    """)
    List<OrderItemsDTO> findItemsByOrderGrouped(@Param("id_order") Long id_order);

}