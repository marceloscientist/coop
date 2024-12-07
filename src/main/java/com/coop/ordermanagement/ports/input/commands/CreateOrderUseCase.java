package com.coop.ordermanagement.ports.input.commands;

import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.domain.models.Order;

public interface CreateOrderUseCase {
    OrderDTO execute(Order order);
}