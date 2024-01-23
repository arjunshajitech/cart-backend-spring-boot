package com.arjunsaji.test.request;

import com.arjunsaji.test.dto.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotBlank
    private String productName;
    @NotBlank
    private long quantity;
    @NotBlank
    private float price;
    @NotBlank
    private ProductCategory category;
}
