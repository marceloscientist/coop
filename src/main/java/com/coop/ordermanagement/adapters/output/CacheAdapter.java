package com.coop.ordermanagement.adapters.output;

import com.coop.ordermanagement.application.dto.OrderDTO;
import com.coop.ordermanagement.ports.output.CachePort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class CacheAdapter implements CachePort {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheAdapter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveToCache(String key, Object value, long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    @Override
    public Optional<List<OrderDTO>> getOrdersFromCache() {
        Object cachedOrders = redisTemplate.opsForValue().get("orders");
        if (cachedOrders instanceof List<?>) {
            return Optional.of((List<OrderDTO>) cachedOrders);
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderDTO> getOrderByIdFromCache(Long orderId) {
        Object cachedOrder = redisTemplate.opsForValue().get(orderId);
        if (cachedOrder instanceof OrderDTO) {
            return Optional.of((OrderDTO) cachedOrder);
        }
        return Optional.empty();
    }

    @Override
    public void cacheOrders(List<OrderDTO> orders) {
        saveToCache("orders", orders, 3600); // Exemplo: TTL de 1 hora
    }

    @Override
    public void cacheOrderById(Long orderId, OrderDTO order) {
        saveToCache(orderId.toString(), order, 3600); // Exemplo: TTL de 1 hora
    }

    @Override
    public void invalidateCache(String key) {
        redisTemplate.delete(key);
    }
}
