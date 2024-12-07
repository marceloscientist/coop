package com.coop.ordermanagement.application.commands;

import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.application.mapper.OrderMapper;
import com.coop.ordermanagement.domain.models.Order;
import com.coop.ordermanagement.ports.input.commands.CreateOrderUseCase;
import com.coop.ordermanagement.ports.output.CachePort;
import com.coop.ordermanagement.ports.output.OrderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort repository;
    private final OrderMapper orderMapper;
    private final CachePort cachePort;

    public CreateOrderService(OrderRepositoryPort repository, OrderMapper orderMapper, CachePort cachePort) {
        this.repository = repository;
        this.orderMapper = orderMapper;
        this.cachePort = cachePort;
    }

    @Override
    public OrderDTO execute(Order order) {
        validateOrder(order);
        Order savedOrder = repository.save(order);
        cachePort.invalidateCache("orders");
        return orderMapper.orderToOrderDTO(savedOrder);
    }

    private void validateOrder(Order order) {
        if (order.getTotalValue() == null || order.getTotalValue() <= 0) {
            throw new IllegalArgumentException("O valor total não pode ser menor ou igual a zero");
        }
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um produto");
        }
    }
}
