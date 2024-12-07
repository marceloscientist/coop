package com.coop.ordermanagement.adapters.input.queries.swagger;

import com.coop.ordermanagement.application.dto.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface GetOrderByIdSwagger {

    @Operation(summary = "Busca um pedido por ID com resiliência, fallback em cache se necessário")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro ao buscar pedido")
    ResponseEntity<OrderDTO>  getOrderById(Long orderId);
}
