package com.coop.ordermanagement.ports.output;

import com.coop.ordermanagement.application.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface CachePort {
    void saveToCache(String key, Object value, long ttl);
    void invalidateCache(String key); // Novo método
    Optional<List<OrderDTO>> getOrdersFromCache(); // Corrigido para lista
    Optional<OrderDTO> getOrderByIdFromCache(Long orderId);
    void cacheOrders(List<OrderDTO> orders); // Corrigido para lista
    void cacheOrderById(Long orderId, OrderDTO order);
}

