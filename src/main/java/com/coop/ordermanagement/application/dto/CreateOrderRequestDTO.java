package com.coop.ordermanagement.application.dto;

import java.util.List;

public record CreateOrderRequestDTO(String externalOrderId, List<ProductDTO> products) {
}
