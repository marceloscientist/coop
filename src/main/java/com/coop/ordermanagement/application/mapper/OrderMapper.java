package com.coop.ordermanagement.application.mapper;

import com.coop.ordermanagement.application.dto.CreateOrderRequestDTO;
import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.domain.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOToOrder(OrderDTO orderDTO);

    List<OrderDTO> ordersToOrderDTOs(List<Order> orders);

    // Mapeamento de CreateOrderRequestDTO para Order
    Order createOrderRequestDTOToOrder(CreateOrderRequestDTO createOrderRequestDTO);
}
