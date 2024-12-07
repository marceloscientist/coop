package com.coop.ordermanagement.application.commands;

import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.application.mapper.OrderMapper;
import com.coop.ordermanagement.domain.models.Order;
import com.coop.ordermanagement.ports.output.CachePort;
import com.coop.ordermanagement.ports.output.OrderRepositoryPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceResilience {

    private final OrderRepositoryPort orderRepositoryPort;
    private final CachePort cachePort;
    private final OrderMapper orderMapper;

    public OrderServiceResilience(OrderRepositoryPort orderRepositoryPort,
                                  CachePort cachePort,
                                  OrderMapper orderMapper) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.cachePort = cachePort;
        this.orderMapper = orderMapper;
    }

    @CircuitBreaker(name = "getOrdersCB", fallbackMethod = "getOrdersFallback")
    @Retry(name = "getOrdersRetry")
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepositoryPort.findAll();
        return orderMapper.ordersToOrderDTOs(orders);
    }

    public List<OrderDTO> getOrdersFallback(Throwable throwable) {
        return cachePort.getOrdersFromCache().orElse(Collections.emptyList());
    }

    @CircuitBreaker(name = "getOrderByIdCB", fallbackMethod = "getOrderByIdFallback")
    @Retry(name = "getOrderByIdRetry")
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return orderMapper.orderToOrderDTO(order);
    }

    public OrderDTO getOrderByIdFallback(Long orderId, Throwable throwable) {
        return cachePort.getOrderByIdFromCache(orderId)
                .orElseThrow(() -> new RuntimeException("Cached order not found with ID: " + orderId));
    }
}
