package com.manager_restaurant.restaurant_manager.service;


import com.manager_restaurant.restaurant_manager.dto.ItensComandasDTO;
import com.manager_restaurant.restaurant_manager.model.Order;
import com.manager_restaurant.restaurant_manager.model.OrderItems;
import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.repository.CommandRepository;
import com.manager_restaurant.restaurant_manager.repository.OrderItemsRepository;
import com.manager_restaurant.restaurant_manager.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;
    private final OrderService orderService;
    private final CommandRepository commandRepository;
    private final ProductsRepository productsRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository, OrderService orderService, CommandRepository commandRepository, ProductsRepository productsRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderService = orderService;
        this.commandRepository = commandRepository;
        this.productsRepository = productsRepository;
    }

    public List<OrderItems> listOrderItems() {
        return orderItemsRepository.findAll();
    }

    public OrderItems searchOrderItem(Long id_order_item) {
        return orderItemsRepository.findById(id_order_item)
                .orElseThrow(() -> new RuntimeException("Item do pedido não encontrado"));
    }

    public OrderItems createOrderItem(OrderItems orderItem) {
        System.out.println("ID ORDER = " + orderItem.getOrder().getId_order());
        System.out.println("ID PRODUCT = " + orderItem.getProduct().getIdProduct());
        OrderItems savedItem = orderItemsRepository.save(orderItem);
        Order order = commandRepository.findById(savedItem.getOrder().getId_order())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Products product = productsRepository.findById(savedItem.getProduct().getIdProduct())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        savedItem.setOrder(order);
        savedItem.setProduct(product);
        orderService.updateTotal(order);
        return savedItem;
    }

    public OrderItems deleteOrderItem(OrderItems OrderItems) {
        OrderItems deletedItem = orderItemsRepository.save(OrderItems);

        Order order = orderItemsRepository.findById(deletedItem.getId_order_item())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado")).getOrder();

        orderService.updateTotal(order);
        orderItemsRepository.delete(deletedItem);
        return deletedItem;
    }

    public List<ItensComandasDTO> listOrderItemsWithDetails() {
        return orderItemsRepository.buscarItensComDetalhes();
    }

    public OrderItems updateOrderItem(Long id_order_item, OrderItems orderItem) {
        OrderItems existingItem = searchOrderItem(id_order_item);
        existingItem.setProduct(orderItem.getProduct());
        existingItem.setQuantity(orderItem.getQuantity());
        existingItem.setNotes(orderItem.getNotes());
        OrderItems updatedItem = orderItemsRepository.save(existingItem);
        orderService.updateTotal(updatedItem.getOrder());
        return updatedItem;
    }
}
