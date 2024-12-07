package com.coop.ordermanagement.adapters.input.queries;

import com.coop.ordermanagement.adapters.input.queries.swagger.GetOrdersSwagger;
import com.coop.ordermanagement.application.commands.OrderServiceResilience;
import com.coop.ordermanagement.application.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/orders")
public class GetOrdersController implements GetOrdersSwagger {

    private final OrderServiceResilience orderServiceResilience;

    @Autowired
    public GetOrdersController(OrderServiceResilience orderServiceResilience) {
        this.orderServiceResilience = orderServiceResilience;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        try {
            List<OrderDTO> orders = orderServiceResilience.getOrders(); // Chamada ao serviço para buscar pedidos
            return ResponseEntity.ok(orders);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null); // Ajuste o tratamento de erro conforme a necessidade
        }
    }
}
