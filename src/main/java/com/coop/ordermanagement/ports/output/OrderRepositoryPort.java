package com.coop.ordermanagement.ports.output;

import com.coop.ordermanagement.domain.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long orderId);
    List<Order> findAll();
}
