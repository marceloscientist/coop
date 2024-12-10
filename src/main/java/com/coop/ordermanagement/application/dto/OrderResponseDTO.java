package com.coop.ordermanagement.application.dto;


import java.util.List;

public record OrderResponseDTO(Long id, String externalOrderId, List<ProductDTO> products, Double totalValue, String status) {
}

