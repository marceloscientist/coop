package com.coop.ordermanagement.application.commands;

import com.coop.ordermanagement.application.dto.CreateOrderRequestDTO;
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
    public OrderDTO execute(CreateOrderRequestDTO createOrderRequestDTO) {
        validateOrder(createOrderRequestDTO);

        Order order = orderMapper.createOrderRequestDTOToOrder(createOrderRequestDTO);

        order.setTotalValue(calculateTotalValue(order));

        Order savedOrder = repository.save(order);
        cachePort.invalidateCache("orders");
        return orderMapper.orderToOrderDTO(savedOrder);
    }

    private void validateOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        if (createOrderRequestDTO.products() == null || createOrderRequestDTO.products().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um produto");
        }
    }

    private Double calculateTotalValue(Order order) {
        // Implementação simples para cálculo do totalValue.
        // Isso pode ser ajustado para integrar com o produto externo A.
        return order.getProducts().stream()
                .mapToDouble(product -> product.getPrice())
                .sum();
    }
}
