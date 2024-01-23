package com.arjunsaji.test.controller.product;

import com.arjunsaji.test.database.ProductRepositoryService;
import com.arjunsaji.test.entity.Product;
import com.arjunsaji.test.request.CreateProductRequest;
import com.arjunsaji.test.response.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepositoryService productRepositoryService;
    final ModelMapper modelMapper;

    public ResponseEntity<?> createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        modelMapper.map(createProductRequest,product);

        Product isProductExists = productRepositoryService.findByProductName(createProductRequest.getProductName());
        if (isProductExists == null) {
            productRepositoryService.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Product created."));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message("Product already exists."));
    }

    public ResponseEntity<?> editProduct(CreateProductRequest createProductRequest, UUID id) {
        Optional<Product> isProductExists = productRepositoryService.findById(id);
        if (isProductExists.isPresent()) {
            modelMapper.map(createProductRequest,isProductExists.get());
            productRepositoryService.save(isProductExists.get());
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Product edited."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Product not found."));
    }

    public List<Product> listAll() {
        return productRepositoryService.findAll();
    }

    public ResponseEntity<?> deleteProduct(UUID id) {
        Optional<Product> isProductExists = productRepositoryService.findById(id);
        if (isProductExists.isPresent()) {
            productRepositoryService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Product deleted."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Product not found."));
    }
}
