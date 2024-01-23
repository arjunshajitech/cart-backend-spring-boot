package com.arjunsaji.test.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    private UUID productId;
    private float price;

    public Cart(UUID userId, UUID productId, float price) {
        this.userId = userId;
        this.productId = productId;
        this.price = price;
    }
}
