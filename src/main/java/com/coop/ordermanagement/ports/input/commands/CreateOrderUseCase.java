package com.coop.ordermanagement.ports.input.commands;

import com.coop.ordermanagement.application.dto.CreateOrderRequestDTO;
import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.domain.models.Order;

public interface CreateOrderUseCase {
    OrderDTO execute(CreateOrderRequestDTO createOrderRequestDTO);
}