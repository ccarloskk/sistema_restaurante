package com.manager_restaurant.restaurant_manager.controller;

import com.manager_restaurant.restaurant_manager.model.Order;
import com.manager_restaurant.restaurant_manager.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> listOrders(){
        return orderService.listOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PutMapping("/{id_order}")
    public Order updateOrder(@PathVariable("id_order") Long id_order, @RequestBody Order order){
        return orderService.updateOrder(id_order, order);
    }

    @DeleteMapping("/{id_order}")
    public void deleteOrder(@PathVariable("id_order") Long id_order){
        orderService.deleteOrder(id_order);
    }
}
