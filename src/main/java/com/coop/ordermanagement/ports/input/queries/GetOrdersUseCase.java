package com.coop.ordermanagement.ports.input.queries;

import com.coop.ordermanagement.application.dto.OrderDTO;

import java.util.List;

public interface GetOrdersUseCase {
    List<OrderDTO> execute();
}