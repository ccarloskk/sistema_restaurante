package com.manager_restaurant.restaurant_manager.controller;

import com.manager_restaurant.restaurant_manager.dto.ItensComandasDTO;
import com.manager_restaurant.restaurant_manager.dto.OrderItemsDTO;
import com.manager_restaurant.restaurant_manager.model.OrderItems;
import com.manager_restaurant.restaurant_manager.service.OrderItemsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_item")
@CrossOrigin(origins = "*")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping("/{id_order}")
    public List<OrderItemsDTO> listOrderItemsByOrderGrouped(@PathVariable Long id_order) {
        return orderItemsService.listOrderItemsByOrderGrouped(id_order);
    }

    @GetMapping("/details")
    public List<ItensComandasDTO> listOrderItemsWithDetails() {
        return orderItemsService.listOrderItemsWithDetails();
    }

    @PostMapping("/CreateOrderItem")
    public OrderItems createOrderItem(@RequestBody OrderItems orderItem){
        return orderItemsService.createOrderItem(orderItem);
    }

    @PutMapping("/{id_order_item}")
    public OrderItems updateOrderItem(@PathVariable Long id_order_item, @RequestBody OrderItems orderItem){
        return orderItemsService.updateOrderItem(id_order_item, orderItem);
    }

    @DeleteMapping("/{id_order_item}")
    public void deleteOrderItem(@PathVariable OrderItems id_order_item){
        orderItemsService.deleteOrderItem(id_order_item);
    }
}
