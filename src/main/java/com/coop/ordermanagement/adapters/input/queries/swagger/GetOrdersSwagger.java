package com.coop.ordermanagement.adapters.input.queries.swagger;

import com.coop.ordermanagement.application.dto.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GetOrdersSwagger {

    @Operation(summary = "Busca todos os pedidos com resiliência, fallback em cache se necessário")
    @ApiResponse(responseCode = "200", description = "Pedidos encontrados com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "500", description = "Erro ao buscar pedidos")
    ResponseEntity<List<OrderDTO>> getOrders();
}
