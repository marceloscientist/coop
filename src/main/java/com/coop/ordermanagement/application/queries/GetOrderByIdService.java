package com.coop.ordermanagement.application.queries;

import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.application.mapper.OrderMapper;
import com.coop.ordermanagement.ports.input.queries.GetOrderByIdUseCase;
import com.coop.ordermanagement.ports.output.OrderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetOrderByIdService implements GetOrderByIdUseCase {

    private final OrderRepositoryPort repository;
    private final OrderMapper orderMapper;

    public GetOrderByIdService(OrderRepositoryPort repository, OrderMapper orderMapper) {
        this.repository = repository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO execute(Long id) {
        return repository.findById(id)
                .map(orderMapper::orderToOrderDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
