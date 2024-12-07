package com.coop.ordermanagement.adapters.input.commands;

import com.coop.ordermanagement.adapters.input.commands.swagger.CreateOrderSwagger;
import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.application.mapper.OrderMapper;
import com.coop.ordermanagement.domain.models.Order;
import com.coop.ordermanagement.ports.input.commands.CreateOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/orders")
public class CreateOrderController implements CreateOrderSwagger {

    private final CreateOrderUseCase createOrderUseCase;
    private final OrderMapper orderMapper;

    public CreateOrderController(CreateOrderUseCase createOrderUseCase, OrderMapper orderMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.orderMapper = orderMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            System.out.println("Received Order: " + orderDTO);
            OrderDTO createdOrder = createOrderUseCase.execute(orderMapper.orderDTOToOrder(orderDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Exception e) {
            System.err.println("Error during order processing: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-lance a exceção para testes
        }
    }
}

