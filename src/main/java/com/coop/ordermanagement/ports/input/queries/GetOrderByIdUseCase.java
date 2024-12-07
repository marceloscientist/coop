package com.coop.ordermanagement.ports.input.queries;

import com.coop.ordermanagement.application.dto.OrderDTO;

public interface GetOrderByIdUseCase {
    OrderDTO execute(Long id);
}