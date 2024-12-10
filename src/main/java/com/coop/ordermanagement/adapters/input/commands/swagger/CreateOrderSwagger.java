package com.coop.ordermanagement.adapters.input.commands.swagger;


import com.coop.ordermanagement.application.dto.CreateOrderRequestDTO;
import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.domain.models.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreateOrderSwagger {

    @Operation(summary = "Cria um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content)
    })
    ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO);
}
