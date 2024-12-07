package com.coop.ordermanagement.adapters.output;

import com.coop.ordermanagement.domain.models.Order;

import java.util.List;
import java.util.Optional;

import com.coop.ordermanagement.infrastructure.repository.JpaOrderRepository;
import com.coop.ordermanagement.ports.output.OrderRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return jpaOrderRepository.findById(orderId);
    }


    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll();
    }


}
