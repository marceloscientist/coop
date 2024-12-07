package com.coop.ordermanagement.domain.models;

import com.coop.ordermanagement.domain.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String externalOrderId;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

        @JoinColumn(name = "order_id")
        private List<Product> products;

        @Column
        private Double totalValue;

        @Column(name = "status", nullable = false)
        @Enumerated(EnumType.STRING)
        private OrderStatus status;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getExternalOrderId() {
                return externalOrderId;
        }

        public void setExternalOrderId(String externalOrderId) {
                this.externalOrderId = externalOrderId;
        }

        public List<Product> getProducts() {
                return products;
        }

        public void setProducts(List<Product> products) {
                this.products = products;
        }

        public Double getTotalValue() {
                return totalValue;
        }

        public void setTotalValue(Double totalValue) {
                this.totalValue = totalValue;
        }

        public OrderStatus getStatus() {
                return status;
        }

        public void setStatus(OrderStatus status) {
                this.status = status;
        }

}
