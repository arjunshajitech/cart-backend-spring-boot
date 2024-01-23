package com.arjunsaji.test.controller.product;

import com.arjunsaji.test.config.Constants;
import com.arjunsaji.test.entity.Product;
import com.arjunsaji.test.request.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(Constants.API_V1_PRODUCT)
public class ProductController {

    final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest) {
       return productService.createProduct(createProductRequest);
    }

    @GetMapping("/list")
    public List<Product> listAll() {
        return productService.listAll();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@RequestBody CreateProductRequest createProductRequest,
                                         @PathVariable("id") UUID id) {
        return productService.editProduct(createProductRequest,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") UUID id) {
        return productService.deleteProduct(id);
    }

}
