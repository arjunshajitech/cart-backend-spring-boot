package com.arjunsaji.test.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    private UUID productId;
    private UUID orderGroupId;
    private float price;

    public Order(UUID userId, UUID productId, UUID orderGroupId, float price) {
        this.userId = userId;
        this.productId = productId;
        this.orderGroupId = orderGroupId;
        this.price = price;
    }
}
