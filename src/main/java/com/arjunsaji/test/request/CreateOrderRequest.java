package com.arjunsaji.test.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderRequest {
    private List<UUID> productId;
}
