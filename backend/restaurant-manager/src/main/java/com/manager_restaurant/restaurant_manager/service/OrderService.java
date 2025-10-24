package com.manager_restaurant.restaurant_manager.service;



import com.manager_restaurant.restaurant_manager.model.Order;
import com.manager_restaurant.restaurant_manager.model.OrderItems;
import com.manager_restaurant.restaurant_manager.model.Products;
import com.manager_restaurant.restaurant_manager.repository.CommandRepository;
import com.manager_restaurant.restaurant_manager.repository.ProductsRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private CommandRepository commandRepository;
    private ProductsRepository productsRepository;

    public OrderService(CommandRepository commandRepository, ProductsRepository productsRepository) {
        this.commandRepository = commandRepository;
        this.productsRepository = productsRepository;
    }

    public BigDecimal updateTotal(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        if (order.getOrder_items() != null) {
            for (OrderItems item : order.getOrder_items()) {
                Long id_product = item.getProduct().getId_product();
                Products product = productsRepository.findById(id_product)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id_product));

                BigDecimal subtotal = product.getProduct_price().multiply(BigDecimal.valueOf(item.getQuantity()));
                total = total.add(subtotal);
            }
            order.setTotal(total);
            commandRepository.save(order);
        }
        return total;
    }

    public List<Order> listOrders() {
        return commandRepository.findAll();
    }

    public Order searchOrder(Long id_order) {
        return commandRepository.findById(id_order)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Order createOrder(Order order) {
        order.setTotal(updateTotal(order));
        return commandRepository.save(order);
    }

    public void deleteOrder(Long id_order) {
        commandRepository.deleteById(id_order);
    }

    public Order updateOrder(Long id_order, Order updatedOrder) {
        Order order = searchOrder(id_order);
        order.setCustomer_name(updatedOrder.getCustomer_name());
        order.setDate_order(updatedOrder.getDate_order());
        order.setStatus(updatedOrder.getStatus());
        order.setTotal(updatedOrder.getTotal());
        return commandRepository.save(order);
    }
}
