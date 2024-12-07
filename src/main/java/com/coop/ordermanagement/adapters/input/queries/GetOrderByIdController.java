package com.coop.ordermanagement.adapters.input.queries;

import com.coop.ordermanagement.adapters.input.queries.swagger.GetOrderByIdSwagger;
import com.coop.ordermanagement.application.commands.OrderServiceResilience;
import com.coop.ordermanagement.application.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class GetOrderByIdController implements GetOrderByIdSwagger {

    private final OrderServiceResilience orderServiceResilience;

    @Autowired
    public GetOrderByIdController(OrderServiceResilience orderServiceResilience) {
        this.orderServiceResilience = orderServiceResilience;
    }

    @Override
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) { // Note que agora o ID é Long
        try {
            OrderDTO dto = orderServiceResilience.getOrderById(orderId);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            throw new RuntimeException("Error retrieving order", ex); // Custom Exception Handling
        }
    }
}
