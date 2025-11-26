package com.manager_restaurant.restaurant_manager.controller;

import com.manager_restaurant.restaurant_manager.dto.ItemsSumDTO;
import com.manager_restaurant.restaurant_manager.model.Order;
import com.manager_restaurant.restaurant_manager.model.OrderItems;
import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.repository.OrderItemsRepository;
import com.manager_restaurant.restaurant_manager.repository.ProductsRepository;
import com.manager_restaurant.restaurant_manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> listOrders(){
        return orderService.listOrders();
    }

    @PostMapping("/updateTotalPublic")
    public BigDecimal updateTotal(@RequestBody List<OrderItems> totalPublicList){
        return orderService.updateTotalPublic(totalPublicList);
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
