package com.coop.ordermanagement.infrastructure.repository;


import com.coop.ordermanagement.domain.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long> { }