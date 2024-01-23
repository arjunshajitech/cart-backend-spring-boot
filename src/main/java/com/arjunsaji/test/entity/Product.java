package com.arjunsaji.test.entity;

import com.arjunsaji.test.dto.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String productName;
    private Long quantity;
    private float price;
    @Enumerated(EnumType.STRING)
    private ProductCategory category = ProductCategory.DEFAULT;
    private String imageUrl;

    public Product(String productName, Long quantity, float price, ProductCategory category) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }
}
