package com.coop.ordermanagement.application.queries;

import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.application.mapper.OrderMapper;
import com.coop.ordermanagement.domain.models.Order;
import com.coop.ordermanagement.ports.input.queries.GetOrdersUseCase;
import com.coop.ordermanagement.ports.output.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrdersService implements GetOrdersUseCase {

    private final OrderRepositoryPort repository;
    private final OrderMapper orderMapper;

    public GetOrdersService(OrderRepositoryPort repository, OrderMapper orderMapper) {
        this.repository = repository;
        this.orderMapper = orderMapper;
    }


    @Override
    public List<OrderDTO> execute() {
        List<Order> orders = repository.findAll();
        return orderMapper.ordersToOrderDTOs(orders);
    }
}
