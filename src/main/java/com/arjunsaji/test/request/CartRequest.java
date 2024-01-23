package com.arjunsaji.test.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CartRequest {
    private UUID productId;
}
